package aaa.tavern.dto.received;

public class ManagerIdDto {
    int managerId;

    protected ManagerIdDto() {

    }

    public ManagerIdDto(int managerId) {
        this.managerId = managerId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

}
