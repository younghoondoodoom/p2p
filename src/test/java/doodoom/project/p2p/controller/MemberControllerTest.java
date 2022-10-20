package doodoom.project.p2p.controller;

import doodoom.project.p2p.configuration.SecurityConfiguration;
import doodoom.project.p2p.domain.Member;
import doodoom.project.p2p.dto.MemberRegisterInput;
import doodoom.project.p2p.service.MemberService;
import doodoom.project.p2p.type.MemberStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@Import(SecurityConfiguration.class)
class MemberControllerTest {

    @MockBean
    private MemberService memberService;
    @Autowired
    private MockMvc mockMvc;
    private static final List<Member> members = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        members.add(Member.builder()
                .email("test@test.com")
                .password("test123!")
                .nickname("test")
                .status(MemberStatus.ACTIVE)
                .build());
    }

    @Test
    public void registerTest() throws Exception {
        //given
        Member member = members.get(0);
        MemberRegisterInput input =
                new MemberRegisterInput(member.getEmail(), member.getPassword(),
                        member.getPassword(), member.getNickname());
        given(memberService.register(input)).willReturn(true);

        //then
        mockMvc.perform(post("/member/register")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("email", input.getEmail())
                .param("password", input.getPassword())
                .param("passwordCheck", input.getPasswordCheck())
                .param("nickname", input.getNickname())
        ).andExpect(status().isFound());
    }
}