package entity;

/**
 *
 * @author Dr M H B Ariyaratne
 */
public enum ProjectStageType {
    Awaiting_Bid_Invitation_Approval,
    Incomplete_Bid_Invitation,
    Bid_Invitation_Rejected,
    Awaiting_DNP_Submission,
    Awaiting_DNP_Approval,
    DNP_Revision,
    DNP_Rejected,
    Awaiting_Cabinet_Submission,
    Awaiting_Cabinet_Approval,
    Cabinet_Approved,
    Cabinet_Rejected,
    Funds_Allocated,
    Ongoing,
    Completed;

    public String getLabel() {
        switch (this) {
            case Awaiting_Bid_Invitation_Approval:
                return "Awaiting Bid Invitation Approval";
            default:
                return this.toString();
        }
    }
}
