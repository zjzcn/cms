package com.zjzcn.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Process;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjzcn.auth.UserManager;
import com.zjzcn.service.FlowManager;

/**
 * @author yuqs
 * @since 2.0
 */
@Controller
@RequestMapping(value = "admin/snaker/flow")
public class FlowController {
    public static final String PARA_PROCESSID = "processId";
    public static final String PARA_ORDERID = "orderId";
    public static final String PARA_TASKID = "taskId";
    public static final String PARA_TASKNAME = "taskName";
    public static final String PARA_METHOD = "method";
    public static final String PARA_NEXTOPERATOR = "nextOperator";
    public static final String PARA_NODENAME = "nodeName";
    public static final String PARA_CCOPERATOR = "ccOperator";
    @Autowired
    private FlowManager flowManager;
    @Autowired
    private UserManager userManager;
//    @Autowired
//    private ApprovalManager manager;
    /**
     * 流程实例查询
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "order", method= RequestMethod.GET)
    public String order(Model model, Page<HistoryOrder> page) {
        flowManager.getEngine().query().getHistoryOrders(page, new QueryFilter());
        model.addAttribute("page", page);
        return "snaker/order";
    }

    /**
     * 抄送实例已读
     * @param id
     * @param url
     * @return
     */
    @RequestMapping(value = "ccread")
    public String ccread(String id, String url) {
//        List<String> list = ShiroUtils.getGroups();
//        list.add(ShiroUtils.getUsername());
//        String[] assignees = new String[list.size()];
//        list.toArray(assignees);
//        flowManager.getEngine().order().updateCCStatus(id, assignees);
        return "redirect:" + url;
    }

	@RequestMapping(value = "process")
    public String process(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        Enumeration<String> paraNames = request.getParameterNames();
        while (paraNames.hasMoreElements()) {
            String element = paraNames.nextElement();
            int index = element.indexOf("_");
            String paraValue = request.getParameter(element);
            if(index == -1) {
                params.put(element, paraValue);
            } else {
                char type = element.charAt(0);
                String name = element.substring(index + 1);
                Object value = null;
                switch(type) {
                    case 'S':
                        value = paraValue;
                        break;
                    case 'I':
                        value = ConvertUtils.convert(paraValue, Integer.class);
                        break;
                    case 'L':
                        value = ConvertUtils.convert(paraValue, Long.class);
                        break;
                    case 'B':
                        value = ConvertUtils.convert(paraValue, Boolean.class);
                        break;
                    case 'D':
                        value = ConvertUtils.convert(paraValue, Date.class);
                        break;
                    case 'N':
                        value = ConvertUtils.convert(paraValue, Double.class);
                        break;
                    default:
                        value = paraValue;
                        break;
                }
                params.put(name, value);
            }
        }
        String processId = request.getParameter(PARA_PROCESSID);
        String orderId = request.getParameter(PARA_ORDERID);
        String taskId = request.getParameter(PARA_TASKID);
        String nextOperator = request.getParameter(PARA_NEXTOPERATOR);
        if (StringUtils.isEmpty(orderId) && StringUtils.isEmpty(taskId)) {
            flowManager.startAndExecute(processId, userManager.getUsername(), params);
        } else {
            String methodStr = request.getParameter(PARA_METHOD);
            int method;
            try {
                method = Integer.parseInt(methodStr);
            } catch(Exception e) {
                method = 0;
            }
            switch(method) {
                case 0://任务执行
                    flowManager.execute(taskId, userManager.getUsername(), params);
                    break;
                case -1://驳回、任意跳转
                    flowManager.executeAndJump(taskId, userManager.getUsername(), params, request.getParameter(PARA_NODENAME));
                    break;
                case 1://转办
                    if(StringUtils.isNotEmpty(nextOperator)) {
                        flowManager.transferMajor(taskId, userManager.getUsername(), nextOperator.split(","));
                    }
                    break;
                case 2://协办
                    if(StringUtils.isNotEmpty(nextOperator)) {
                        flowManager.transferAidant(taskId, userManager.getUsername(), nextOperator.split(","));
                    }
                    break;
                default:
                    flowManager.execute(taskId, userManager.getUsername(), params);
                    break;
            }
        }
        String ccOperator = request.getParameter(PARA_CCOPERATOR);
        if(StringUtils.isNotEmpty(ccOperator)) {
            flowManager.getEngine().order().createCCOrder(orderId, userManager.getUsername(), ccOperator.split(","));
        }
        return "redirect:/admin/snaker/task/active.do";
    }

    /**
     * 通用的流程展现页面入口
     * 将流程中的各环节表单以tab+iframe方式展现
     */
    @RequestMapping(value = "all")
    public String all(Model model, String processId, String orderId, String taskId) {
		model.addAttribute("processId", processId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("taskId", taskId);
        if(StringUtils.isNotEmpty(processId)) {
            model.addAttribute("process", flowManager.getEngine().process().getProcessById(processId));
        }
        if(StringUtils.isNotEmpty(orderId)) {
            model.addAttribute("order", flowManager.getEngine().query().getOrder(orderId));
        }
        if(StringUtils.isNotEmpty(taskId)) {
            model.addAttribute("task", flowManager.getEngine().query().getTask(taskId));
        }
        return "admin/flow/all";
    }

    /**
     * 节点信息以json格式返回
     * all页面以节点信息构造tab及加载iframe
     */
    @RequestMapping(value = "node")
    @ResponseBody
    public Object node(String processId) {
        Process process = flowManager.getEngine().process().getProcessById(processId);
        List<TaskModel> models = process.getModel().getModels(TaskModel.class);
        List<TaskModel> viewModels = new ArrayList<TaskModel>();
        for(TaskModel model : models) {
            TaskModel viewModel = new TaskModel();
            viewModel.setName(model.getName());
            viewModel.setDisplayName(model.getDisplayName());
            viewModel.setForm(model.getForm());
            viewModels.add(viewModel);
        }
        return viewModels;
    }

    /**
     * 由于审批类流程在各业务系统中经常出现，至此本方法是统一审批的url
     * 如果审批环节能够统一，建议使用该方法返回统一审批页面
     */
    @RequestMapping(value = "approval")
    public String approval(Model model, String processId, String orderId, String taskId, String taskName) {
		model.addAttribute("processId", processId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("taskId", taskId);
        if(StringUtils.isNotEmpty(taskId)) {
            return "snaker/approval";
        } else {
//            model.addAttribute("approvals", manager.findByFlow(orderId, taskName));
            return "snaker/approvalView";
        }
    }

    /**
     * 审批环节的提交处理
     * 其中审批表可根据具体审批的业务进行定制，此处仅仅是举例
     */
//    @RequestMapping(value = "doApproval", method = RequestMethod.POST)
//    public String doApproval(Approval model) {
////        model.setOperateTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
////        model.setOperator(ShiroUtils.getUsername());
////        manager.save(model);
////
////        Map<String, Object> params = new HashMap<String, Object>();
////        params.put("result", model.getResult());
////        flowManager.execute(model.getTaskId(), ShiroUtils.getUsername(), params);
//        return "redirect:/snaker/task/active";
//    }
}
