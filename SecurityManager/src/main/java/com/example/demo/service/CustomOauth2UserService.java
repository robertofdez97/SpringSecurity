package com.example.demo.service;

import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.client.UserClient;
import com.example.demo.model.OAuth2UserInfo;
import com.example.demo.model.OAuthUserFactory;
import com.example.demo.model.Provider;
import com.example.demo.model.User;
import com.example.demo.model.UserPrincipal;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserClient userClient;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauthUser = super.loadUser(userRequest);
		try {
			return processUser(userRequest, oauthUser);
		} catch (Exception e) {
			throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
		}
	}

	private OAuth2User processUser(OAuth2UserRequest userRequest, OAuth2User oauthUser) throws AuthenticationException {
		OAuth2UserInfo oAuth2UserInfo = OAuthUserFactory
				.getOAuth2UserInfo(userRequest.getClientRegistration().getRegistrationId(), oauthUser.getAttributes());
		Optional<User> userOptional = userClient.getUserByEmail(oAuth2UserInfo.getEmail());
		User userToRet;
		if (!userOptional.isEmpty()) {
			User user = userOptional.get();
			CheckUserALreadyLoginWithDifferentProvider(userRequest, user);
			userToRet = user;
		}
		else {
			User user = createUser(oAuth2UserInfo, userRequest);
			userToRet = userClient.save(user).orElseThrow(() -> new AuthenticationException());	
		}
		return UserPrincipal.create(userToRet);
	}

	private User createUser(OAuth2UserInfo oauthUser, OAuth2UserRequest userRequest) throws AuthenticationException {
		User user = new User();

        user.setProvider(Provider.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase()));
        user.setFullName(oauthUser.getName());
        user.setEmail(oauthUser.getEmail());
		return user;
	}

	private void CheckUserALreadyLoginWithDifferentProvider(OAuth2UserRequest userRequest, User user)
			throws AuthenticationException {
		if (!user.getProvider().equals(Provider.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase()))) {
			throw new AuthenticationException("Looks like you're signed up with " + user.getProvider()
					+ " account. Please use your " + user.getProvider() + " account to login.");
		}
	}

}
