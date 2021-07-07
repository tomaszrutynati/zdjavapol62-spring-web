package pl.sda.matchbetapp.web.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SelectOption {
    private Long id;
    private String label;
}
