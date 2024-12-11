package com.Projet.Projet.controller;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WsChatMessage {

    private String sender;
    private String content;
    private WsChatMessageType type;

}
