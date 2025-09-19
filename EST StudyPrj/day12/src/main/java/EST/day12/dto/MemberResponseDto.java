package EST.day12.dto;

import EST.day12.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private final Long id;
    private final String name;

    @Builder
    public MemberResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 엔티티 → DTO 변환용
    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }
}
