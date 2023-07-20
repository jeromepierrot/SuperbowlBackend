package fr.jpierrot.superbowlbackend.pojo.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@RequiredArgsConstructor
public class CommentatorRegisterRequest extends RegisterRequest {
}
