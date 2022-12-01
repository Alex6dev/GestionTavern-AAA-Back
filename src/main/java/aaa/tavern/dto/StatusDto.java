package aaa.tavern.dto;

public class StatusDto {
    String type;
    String message;

    public StatusDto(int type, String message) {
        switch(type){
            case 1:
            this.type = "info";
            break;
            case 2:
            this.type = "warning";
            break;            
            case 3:
            this.type = "error";
            break;
            
        }
        this.message = message;
    }
    
}
