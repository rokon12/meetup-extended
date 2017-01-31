package com.bazlur.meetup.extended.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
@Entity
@Data
public class User implements Serializable {
    @GeneratedValue
    @Id
    private Long id;

    private String email;
    private String name;
    private String twitter;
    private String github;
    private String avatarUrl;

    @Column
    @Lob
    private String bio;

    @OneToMany(mappedBy = "speaker", cascade = CascadeType.ALL)
    private Set<Submission> submissions;
}
