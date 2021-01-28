package com.ssk.book.springboot.config.auth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ssk.book.springboot.config.auth.dto.OAuthAttributes;
import com.ssk.book.springboot.config.auth.dto.SessionUser;
import com.ssk.book.springboot.domain.user.User;
import com.ssk.book.springboot.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //스프링 설정부분에서 DI
@Service //서비스 어노테이션으로 등록해서 scan 
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{

		private final UserRepository userRepository;
		private final HttpSession httpSession;
		
		@Override
		public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
			OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
			
			OAuth2User oAuth2User = delegate.loadUser(userRequest);
			
			String registrationId = userRequest.getClientRegistration().getRegistrationId();
			String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
			
			OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		
			User user = saveOrUpdate(attributes);
			
			httpSession.setAttribute("user", new SessionUser(user));
			
			return new DefaultOAuth2User(
						Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
						attributes.getAttributes(),
						attributes.getNameAttributeKey());
		}
		
		private User saveOrUpdate(OAuthAttributes attributes) {
			User user = userRepository.findByEmail(attributes.getEmail())
					.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
					.orElse(attributes.toEntity());

			return userRepository.save(user);
		}
	
}
