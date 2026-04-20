package model;

public class VisitorEntry {
    private String visitorName;
    private String ticketCode;
    private String entryTime;

    public VisitorEntry(String visitorName, String ticketCode, String entryTime) {
        this.visitorName = visitorName;
        this.ticketCode = ticketCode;
        this.entryTime = entryTime;
    }

    public String getVisitorName() { return visitorName; }
    public String getTicketCode() { return ticketCode; }
    public String getEntryTime() { return entryTime; }
}
