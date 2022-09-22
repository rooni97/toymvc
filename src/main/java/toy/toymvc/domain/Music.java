package toy.toymvc.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Music {

    @Id @GeneratedValue
    @Column(name = "music_id")
    private Long id;

    private String url;

    private String playName;

    private String artistName;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Music() {
    }
}
