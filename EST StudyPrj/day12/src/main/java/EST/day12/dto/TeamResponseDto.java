package EST.day12.dto;

import EST.day12.domain.Team;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TeamResponseDto {
    private final Long id;
    private final String name;
    private final List<MemberResponseDto> members;

    @Builder
    public TeamResponseDto(Long id, String name, List<MemberResponseDto> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    // 엔티티 → DTO 변환용
    public static TeamResponseDto from(Team team) {
        return TeamResponseDto.builder()
                .id(team.getId())
                .name(team.getName())
                .members(team.getMembers()
                        .stream()
                        // ClassName::methodName
                        .map(MemberResponseDto::from)
                        .toList())
                .build();
    }
}
