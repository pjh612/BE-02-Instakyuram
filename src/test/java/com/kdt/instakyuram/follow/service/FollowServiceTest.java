package com.kdt.instakyuram.follow.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kdt.instakyuram.follow.domain.Follow;
import com.kdt.instakyuram.follow.domain.FollowRepository;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {

	@InjectMocks
	private FollowService followService;

	@Mock
	private FollowRepository followRepository;

	@Test
	@DisplayName("팔로잉한 목록 조회 테스트 [1L의 식별자를 가진 사용자가 2L,3L을 가진 멤버를 팔로잉 할때]")
	void testGettingFollowingIds() {
		Long memberId = 1L;
		Long targetAId = 2L;
		Long targetBId = 3L;

		List<Follow> followingTargetIds = List.of(
			Follow.builder()
				.memberId(memberId)
				.targetId(targetAId).
				build(),
			Follow.builder()
				.memberId(memberId)
				.targetId(targetBId)
				.build()
		);

		//given
		given(followRepository.findByMemberId(memberId)).willReturn(followingTargetIds);

		//when
		List<Long> followingIds = followService.findByFollowingIds(memberId);

		//then
		assertThat(followingIds.size()).isEqualTo(followingTargetIds.size());
		assertThat(followingIds).contains(targetAId, targetBId);

		verify(followRepository, times(1)).findByMemberId(memberId);
	}

	@Test
	@DisplayName("나를 따르는 사람의 수 구하기 =: follower 수")
	void testCountMyFollower() {
		//given
		Long expectedMyFollower = 10L;
		Long myId = 1L;

		given(followRepository.countByTargetId(myId)).willReturn(expectedMyFollower);

		//when
		Long myFollower = followRepository.countByTargetId(myId);

		//then
		Assertions.assertNotNull(myFollower);
		assertThat(myFollower).isEqualTo(expectedMyFollower);

		verify(followRepository, times(1)).countByTargetId(myId);
	}

	@Test
	@DisplayName("내가 따르는 사람의 수 구하기 =: following 수")
	void testCountMyFollowing() {
		//given
		Long expectedMyFollower = 10L;
		Long myId = 1L;

		given(followRepository.countByTargetId(myId)).willReturn(expectedMyFollower);

		//when
		Long myFollowing = followRepository.countByTargetId(myId);

		//then
		Assertions.assertNotNull(myFollowing);
		assertThat(myFollowing).isEqualTo(expectedMyFollower);

		verify(followRepository, times(1)).countByTargetId(myId);
	}

}