package com.bazlur.meetup.extended.integration.meetup;

import lombok.Data;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/30/17.
 */
@Data
public class Meetup {
    private String status;
    private String time;
    private long yesRsvpCount;
    private String link;
    private String description;
}
