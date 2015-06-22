package models;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Created by ssa on 2015-06-14 22:47
 */

@Data
public class Visitor {

    private String visitorId;
    private Integer siteId;
    private Long visitTs;

    public Visitor(){}

    @Builder
    public Visitor( @NonNull String visitorId, @NonNull Integer siteId, Long visitTs){
        this.visitorId = visitorId;
        this.siteId = siteId;
        this.visitTs = visitTs;
    }

}
