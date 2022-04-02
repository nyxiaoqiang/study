package com.study.security.boot08security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    private Integer id;
    private String userName;//用户名
    private String passWord;//密码

    private List<Role> roles;//该用户对应的角色




    /**
     * 返回用户的权限集合。
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * 返回账号的密码
     * @return
     */
    @Override
    public String getPassword() {
        return passWord;
    }

    /**
     * 返回账号的用户名
     * @return
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 账号是否失效，true:账号有效，false账号失效。
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * 账号是否被锁，true:账号没被锁，可用；false：账号被锁，不可用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账号认证是否过期，true:没过期，可用；false：过期，不可用
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否可用，true:可用，false:不可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}

