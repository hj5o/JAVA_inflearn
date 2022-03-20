package hello.hellospring.Repository;

import hello.hellospring.Domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository Repository = new MemoryMemberRepository();

    //테스트가 끝날 때 메모리를 지워줘야함
    @AfterEach
    public void afterEach() {
        Repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        Repository.save(member);

        Member result = Repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        Repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        Repository.save(member2);

        Member result = Repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        Repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        Repository.save(member2);

        List<Member> result = Repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
