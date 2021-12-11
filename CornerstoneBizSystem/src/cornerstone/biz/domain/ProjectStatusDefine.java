package cornerstone.biz.domain;

import java.util.Date;
import java.util.List;

import cornerstone.biz.annotations.DomainDefineValid;
import cornerstone.biz.annotations.DomainDefineValid.UniqueKey;
import cornerstone.biz.annotations.DomainFieldValid;
import cornerstone.biz.domain.query.BizQuery;
import jazmin.driver.jdbc.smartjdbc.annotations.DomainDefine;
import jazmin.driver.jdbc.smartjdbc.annotations.ForeignKey;
import jazmin.driver.jdbc.smartjdbc.annotations.QueryDefine;
import jazmin.driver.jdbc.smartjdbc.annotations.QueryField;
/**
 * 项目状态定义
 * 
 * @author 杜展扬 2018-07-29
 *
 */
@DomainDefine(domainClass = ProjectStatusDefine.class)
@DomainDefineValid(comment ="项目状态定义",uniqueKeys={@UniqueKey(fields={"projectId","objectType","name"})})
public class ProjectStatusDefine extends BaseDomain{
    //
    public static final int TYPE_开始状态 = 1;
    public static final int TYPE_进行状态= 2;
    public static final int TYPE_结束状态= 3;
    //
    @ForeignKey(domainClass=Company.class)
	public int companyId;
    
    @ForeignKey(domainClass=Project.class)
    @DomainFieldValid(comment="项目",required=true,canUpdate=true)
    public int projectId;
    
    @DomainFieldValid(comment="对象类型",required=true,canUpdate=true)
    public int objectType;
    
    @DomainFieldValid(comment="名称",required=true,canUpdate=true,maxValue=64)
    public String name;
    
    @DomainFieldValid(comment="类型",canUpdate=true,dataDict="ProjectStatusDefine.type")
    public int type;
    
    @DomainFieldValid(comment="转移状态列表",canUpdate=true,maxValue=512)
    public List<Integer> transferTo;
    
    @DomainFieldValid(comment="颜色",required=true,canUpdate=true,maxValue=32)
    public String color;
    
    @DomainFieldValid(comment="备注",canUpdate=true,maxValue=512)
    public String remark;
    
    //注意：创建任务的时候不管 creater,owner,role_xxx
    @DomainFieldValid(comment="设置责任人",canUpdate=true,maxValue=512)//当状态修改到这个状态时设置(当bug修改为已解决时，需要把责任人设置为创建人，自动)
    public List<String> setOwnerList;
    
  //注意：创建任务的时候不管
    @DomainFieldValid(comment="检查字段是否设置",canUpdate=true,maxValue=512)//到这个状态前，判断以下字段是否设置（举个例子把bug的状态修改为不予处理。则必须把原因给设上,否则报错）对应t_project_field_define表的id
    public List<Integer> checkFieldList;
    
    @DomainFieldValid(comment="有权限编辑此状态的角色",canUpdate=true,maxValue=512)//谁有权限编辑此状态 默认都可以 creater,owner,role_xx
    public List<String> permissionOwnerList;
    
    @ForeignKey(domainClass=Account.class)
    @DomainFieldValid(comment="创建人",required=true,canUpdate=true)
    public int createAccountId;
    
    @ForeignKey(domainClass=Account.class)
    @DomainFieldValid(comment="更新人 ",required=true,canUpdate=true)
    public int updateAccountId;
    
    //
    //   
    public static class ProjectStatusDefineInfo extends ProjectStatusDefine{
    //

    }
    //
    //   
    @QueryDefine(domainClass=ProjectStatusDefineInfo.class)
    public static class ProjectStatusDefineQuery extends BizQuery{
        //
        public Integer id;
        
        public Integer companyId;

        public Integer projectId;

        public Integer objectType;

        public String name;

        public Integer type;

        public String transferTo;

        public String color;

        public String remark;

        public Integer createAccountId;

        public Integer updateAccountId;

        @QueryField(operator=">=",field="createTime")
        public Date createTimeStart;
        
        @QueryField(operator="<=",field="createTime")
        public Date createTimeEnd;

        @QueryField(operator=">=",field="updateTime")
        public Date updateTimeStart;
        
        @QueryField(operator="<=",field="updateTime")
        public Date updateTimeEnd;

        //in or not in fields
        @QueryField(operator="in",field="type")
        public int[] typeInList;
        
        @QueryField(operator="not in",field="type")
        public int[] typeNotInList;
        

        //ForeignQueryFields
     
        //inner joins
        //sort
        public int idSort;
        public int projectIdSort;
        public int objectTypeSort;
        public int nameSort;
        public int typeSort;
        public int transferToSort;
        public int colorSort;
        public int remarkSort;
        public int createAccountIdSort;
        public int updateAccountIdSort;
        public int createTimeSort;
        public int updateTimeSort;
    }

}