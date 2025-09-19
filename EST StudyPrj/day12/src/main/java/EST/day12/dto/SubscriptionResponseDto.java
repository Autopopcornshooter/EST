package EST.day12.dto;

import EST.day12.domain.Member;
import EST.day12.domain.Subscription;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SubscriptionResponseDto {
    private Long id;
    private String contentName;
    private List<MemberResponseDto> memberList=new ArrayList<>();

    @Builder
    public SubscriptionResponseDto(Long id, String contentName, List<MemberResponseDto> memberList){
        this.id=id;
        this.contentName=contentName;
        this.memberList=memberList;
    }

    public static SubscriptionResponseDto from(Subscription subscription){
        return SubscriptionResponseDto.builder()
                .id(subscription.getId())
                .contentName(subscription.getContentName())
                .memberList(subscription.getMembers()
                        .stream()
                        .map(MemberResponseDto::from)
                        .toList())
                .build();
    }
}
