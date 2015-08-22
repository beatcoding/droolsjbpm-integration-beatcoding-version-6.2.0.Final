package org.kie.services.client.serialization.jaxb.impl.query;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kie.services.client.serialization.jaxb.impl.task.JaxbTaskSummary;

@XmlRootElement(name="query-task-info")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbQueryTaskInfo {

    @XmlElement
    private Long processInstanceId;
    
    @XmlElement
    private List<JaxbTaskSummary> taskSummaries = new ArrayList<JaxbTaskSummary>();
   
    @XmlElement
    private List<JaxbVariableInfo> variables = new ArrayList<JaxbVariableInfo>();

    public JaxbQueryTaskInfo() {
        // default for JAXB
    }
    
    public JaxbQueryTaskInfo(long procInstId) {
        this.processInstanceId = procInstId;
    }
    
    public Long getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId( Long processInstanceId ) {
        this.processInstanceId = processInstanceId;
    }

    public List<JaxbTaskSummary> getTaskSummaries() {
        return taskSummaries;
    }

    public void setTaskSummaries( List<JaxbTaskSummary> taskSummaries ) {
        this.taskSummaries = taskSummaries;
    }

    public List<JaxbVariableInfo> getVariables() {
        return variables;
    }

    public void setVariables( List<JaxbVariableInfo> variables ) {
        this.variables = variables;
    }
    
}
