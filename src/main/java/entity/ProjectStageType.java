package entity;

/**
 *
 * @author Dr M H B Ariyaratne
 */
public enum ProjectStageType {
    Awaiting_Bid_Invitation_Approval,
    Incomplete_Bid_Invitation,
    Bid_Invitation_Rejected,
    Approved_Bid_Invitation,
    Bids_Open,
    Bids_Closed,
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
