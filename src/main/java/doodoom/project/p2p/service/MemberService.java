package doodoom.project.p2p.service;

import doodoom.project.p2p.dto.MemberRegisterInput;

public interface MemberService {

    boolean register(MemberRegisterInput input);
}
