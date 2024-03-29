package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer projectYear;

    @ManyToOne
    private Area province;

    @ManyToOne
    Item category;

    @ManyToOne
    Item procurementEntity;

    @ManyToOne
    Item subCategory;

    @ManyToOne
    private Project parentProject;

    @ManyToOne
    private Project referenceProject;

    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    @ManyToOne
    private Institution institution;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProjectProvince> projectProvinces;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProjectDistrict> projectDistricts;

    private String fileNumber;

    private String title;

    @Lob
    private String description;

    private Double allocation;

    @ManyToOne
    private Item allocationUnit;

    Double quantity;

    @Lob
    private String remarks;

    @ManyToOne
    private Item biddingType;

    @OneToMany
    private List<Item> keywords;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date bidOpeningAt;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date bidClosingAt;

    @Enumerated(EnumType.STRING)
    private ProjectStageType currentStageType;

    //Created Properties
    @ManyToOne
    private WebUser creater;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;

    //Editor Properties
    @ManyToOne
    private WebUser lastEditor;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastEditAt;

    //Retairing properties
    private boolean retired;
    @ManyToOne
    private WebUser retirer;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date retiredAt;
    @Lob
    private String retireComments;

    //Created Properties
    private boolean approvedForBidInvitation;
    @ManyToOne
    private WebUser approvedForBidInvitationBy;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date approvedForBidInvitationAt;
    @Lob
    private String approvedForBidInvitationComments;

    //Created Properties
    private boolean rejectedFromBidInvitation;
    @ManyToOne
    private WebUser rejectedFromBidInvitationBy;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date rejectedFromBidInvitationAt;
    @Lob
    private String rejectedFromBidInvitationComments;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "File No " + fileNumber + " ";
    }

    public Item getCategory() {
        return category;
    }

    public void setCategory(Item category) {
        this.category = category;
    }

    public Item getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Item subCategory) {
        this.subCategory = subCategory;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Project getParentProject() {
        return parentProject;
    }

    public void setParentProject(Project parentProject) {
        this.parentProject = parentProject;
    }

    public Project getReferenceProject() {
        return referenceProject;
    }

    public void setReferenceProject(Project referenceProject) {
        this.referenceProject = referenceProject;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public List<Item> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Item> keywords) {
        this.keywords = keywords;
    }

    public Date getBidOpeningAt() {
        return bidOpeningAt;
    }

    public void setBidOpeningAt(Date bidOpeningAt) {
        this.bidOpeningAt = bidOpeningAt;
    }

    public Date getBidClosingAt() {
        return bidClosingAt;
    }

    public void setBidClosingAt(Date bidClosingAt) {
        this.bidClosingAt = bidClosingAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(Integer projectYear) {
        this.projectYear = projectYear;
    }

    public Area getProvince() {
        return province;
    }

    public void setProvince(Area province) {
        this.province = province;
    }

    public List<ProjectProvince> getProjectProvinces() {
        return projectProvinces;
    }

    public void setProjectProvinces(List<ProjectProvince> projectProvinces) {
        this.projectProvinces = projectProvinces;
    }

    public List<ProjectDistrict> getProjectDistricts() {
        return projectDistricts;
    }

    public void setProjectDistricts(List<ProjectDistrict> projectDistricts) {
        this.projectDistricts = projectDistricts;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAllocation() {
        return allocation;
    }

    public void setAllocation(Double allocation) {
        this.allocation = allocation;
    }

    public Item getAllocationUnit() {
        return allocationUnit;
    }

    public void setAllocationUnit(Item allocationUnit) {
        this.allocationUnit = allocationUnit;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Item getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(Item biddingType) {
        this.biddingType = biddingType;
    }

    public ProjectStageType getCurrentStageType() {
        return currentStageType;
    }

    public void setCurrentStageType(ProjectStageType currentStageType) {
        this.currentStageType = currentStageType;
    }

    public WebUser getCreater() {
        return creater;
    }

    public void setCreater(WebUser creater) {
        this.creater = creater;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public WebUser getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(WebUser lastEditor) {
        this.lastEditor = lastEditor;
    }

    public Date getLastEditAt() {
        return lastEditAt;
    }

    public Item getProcurementEntity() {
        return procurementEntity;
    }

    public void setProcurementEntity(Item procurementEntity) {
        this.procurementEntity = procurementEntity;
    }

    public void setLastEditAt(Date lastEditAt) {
        this.lastEditAt = lastEditAt;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public WebUser getRetirer() {
        return retirer;
    }

    public void setRetirer(WebUser retirer) {
        this.retirer = retirer;
    }

    public Date getRetiredAt() {
        return retiredAt;
    }

    public void setRetiredAt(Date retiredAt) {
        this.retiredAt = retiredAt;
    }

    public String getRetireComments() {
        return retireComments;
    }

    public void setRetireComments(String retireComments) {
        this.retireComments = retireComments;
    }

    public WebUser getApprovedForBidInvitationBy() {
        return approvedForBidInvitationBy;
    }

    public void setApprovedForBidInvitationBy(WebUser approvedForBidInvitationBy) {
        this.approvedForBidInvitationBy = approvedForBidInvitationBy;
    }

    public Date getApprovedForBidInvitationAt() {
        return approvedForBidInvitationAt;
    }

    public void setApprovedForBidInvitationAt(Date approvedForBidInvitationAt) {
        this.approvedForBidInvitationAt = approvedForBidInvitationAt;
    }

    public String getApprovedForBidInvitationComments() {
        return approvedForBidInvitationComments;
    }

    public void setApprovedForBidInvitationComments(String approvedForBidInvitationComments) {
        this.approvedForBidInvitationComments = approvedForBidInvitationComments;
    }

    public boolean isApprovedForBidInvitation() {
        return approvedForBidInvitation;
    }

    public void setApprovedForBidInvitation(boolean approvedForBidInvitation) {
        this.approvedForBidInvitation = approvedForBidInvitation;
    }

    public boolean isRejectedFromBidInvitation() {
        return rejectedFromBidInvitation;
    }

    public void setRejectedFromBidInvitation(boolean rejectedFromBidInvitation) {
        this.rejectedFromBidInvitation = rejectedFromBidInvitation;
    }

    public WebUser getRejectedFromBidInvitationBy() {
        return rejectedFromBidInvitationBy;
    }

    public void setRejectedFromBidInvitationBy(WebUser rejectedFromBidInvitationBy) {
        this.rejectedFromBidInvitationBy = rejectedFromBidInvitationBy;
    }

    public Date getRejectedFromBidInvitationAt() {
        return rejectedFromBidInvitationAt;
    }

    public void setRejectedFromBidInvitationAt(Date rejectedFromBidInvitationAt) {
        this.rejectedFromBidInvitationAt = rejectedFromBidInvitationAt;
    }

    public String getRejectedFromBidInvitationComments() {
        return rejectedFromBidInvitationComments;
    }

    public void setRejectedFromBidInvitationComments(String rejectedFromBidInvitationComments) {
        this.rejectedFromBidInvitationComments = rejectedFromBidInvitationComments;
    }

    

}
