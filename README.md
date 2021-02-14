# spring-security 角色權限管理模板
> 在spring中可使用spring-security角色控制、權限控管等，可從官網找到，在 @EnableGlobalMethodSecurity 這個註釋中可使用securedEnabled、prePostEnabled 或 jsr250Enabled 3 種方式來達到 API 或 Method 權限控管，背後是使用 AOP 相關技術實作出來的，但在使用這些之前需先實作 WebSecurityConfigurerAdapter 設置相關的設定，至於如何實作請自己google，或參考內容中的 WebSecurityConfig 自行測試、驗證

> 在 spring 的安全機制中可分為 Authority 及 Role 2種機制，別把2種東西搞混了

> securedEnabled 對應的是 @Secured 這個註釋，其對應的是 Authority 非 Role 

> prePostEnabled 對應的是 @PreAuthorize 這個註釋，可使用表達式使用 Role 或 Authority

> jsr250Enabled 對應的是 @RolesAllowed 這個註釋，其對應的是 Role 非 Authority 

> securedEnabled、jsr250Enabled 是做不到「xxx AND xxx」的，如希望認證時需要含有 2 種以上的角色或權限請使用 prePostEnabled

> 此模板目前採用 h2 存取使用者資訊，其 SQL 指令在 resources 底下的 data.sql，可自行調整使用者資料測試(目前使用者密碼皆為1234)

> 在前端部分已初步用 Angular4 建置簡易的 SSO ，主要使用 localStorage 存取使用者 token 以達到辨識目前使用者、角色，並可使用登入的角色自行測試權限的 API，切記,在 localStorage 存使用者 token 非安全機制，請示情況改變作法、設計

## Backend(Java8)
> 主要分成2塊API，帳戶API、權限API，目的為驗證不同角色身份因權限不同，使之在特定的API下使用狀況
> 
##### 帳戶API(使用Dylan帳戶)
帳戶登入
```
> curl http://localhost:8090/spring-security-angular-auth/api/account/login
{
    "id": 1,
    "username": "Dylan"
}
```
帳戶登出
```
> curl http://localhost:8090/spring-security-angular-auth/api/account/logout
"Bye!"
```

##### 權限API(使用Dylan帳戶)
找出所有帳戶(需是Admin角色)
```
> curl http://localhost:8090/api/findAll
[
    {
        "id": 1,
        "username": "Dylan"
    },
    {
        "id": 2,
        "username": "Emily"
    },
    {
        "id": 3,
        "username": "Samuel"
    },
    {
        "id": 4,
        "username": "Mike"
    }
]
```
找出目前使用的帳戶(需擁有Admin或User角色)
```
> curl http://localhost:8090/api/findSelf
{
    "id": 1,
    "username": "Dylan"
}
```
找出目前使用的帳戶名稱(需同時擁有Admin及User角色)
```
> curl http://localhost:8090/api/findUserName
Dylan
```

## Frontend(Angular4)
> 主要頁面為2頁，目的為帳戶登入、帳戶登出及權限API測試用，因僅提供測試，故畫面簡陋，請自行增加設計

