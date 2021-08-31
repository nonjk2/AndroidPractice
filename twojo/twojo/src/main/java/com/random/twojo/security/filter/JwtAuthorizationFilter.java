package com.random.twojo.security.filter;

import com.random.twojo.model.entity.MemberEntity;
import com.random.twojo.security.auth.PrincipalDetails;
import com.random.twojo.security.jwt.JwtToken;
import com.random.twojo.service.MemberService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter{


    private MemberService memberService;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberService memberService) {
        super(authenticationManager);
        this.memberService = memberService;

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String jwtheader = request.getHeader("Authorization");

        if(jwtheader == null || !jwtheader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader("Authorization").replace("Bearer ", ""); //베리어값을 다른값으로 해버리면 해독에 혼란 스턴 3초




        Integer idx =
                JwtToken.getTokenFromJwtSting(jwtToken);


        if( idx != null ) {

            MemberEntity user = memberService.selectMemberOne(idx);

            PrincipalDetails principalDetails = new PrincipalDetails(user);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            SecurityContextHolder.getContext().getAuthentication();
            chain.doFilter(request, response);
        }
    }
}
