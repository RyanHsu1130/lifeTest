# lifeTest
使用框架：Spring Boot、Spring Data JPA、Hibernate 及其他需要用到的 Library

專案類型：Maven

資料庫：H2

 

實作以下 3 支 RESTful API，格式為 Json，資料庫欄位自行定義，上傳至 GitHub，並回覆 GitHub URL：

(1) 使用者登入

(2) 使用者登出

(3) 提款



## 執行

```
run as Java Application on org.lifetest.application
```

## API

1. login - 登入

   ```
   POST - localhost:8080/lifetest/auth/login
   BODY - {
       "userName" : String,
       "password" : String
   }
   RETURN 200 - String: JWT TOKEN
   ERR 500 -
   [
       輸入參數格式不符 - input有誤,
       使用者不存在或使用者已登入 - 帳號找不到或使用者已經登入,
       帳號密碼輸入錯誤 - 密碼錯誤
   ]
   ```

2. logout - 登出

   ```
   GET - localhost:8080/lifetest/auth/logout
   HEADER - 
   	Authorization: JWT TOKEN
   RETURN 200 - Boolean: True
   ERR 500 -
   [
       使用者不存在或使用者已登入 - 帳號找不到或使用者已經登入,
   ]
   	403 -
   [
       無Token - header沒有JWT Token或是Token有誤
   ]	
   ```

3. deposit - 存款

   ```
   GET - localhost:8080/lifetest/deposit
   HEADER - 
   	Authorization: JWT TOKEN
   RETURN 200 - String: 已存款 {0}，尚有餘額 {1} 
   ERR 500 -
   [
   	輸入參數格式不符 - input有誤,
   	使用已登出，請重新登入 - 已經呼叫登出API
       使用者不存在或使用者已登入 - 帳號找不到或使用者已經登入,
   ]
   	403 -
   [
       無Token - header沒有JWT Token或是Token有誤
   ]	
   ```

4. withdraw - 提款

   ```
   GET - localhost:8080/lifetest/withdraw
   HEADER - 
   	Authorization: JWT TOKEN
   RETURN 200 - String: 已提款 {0}，尚有餘額 {1}
   ERR 500 -
   [
   	輸入參數格式不符 - input有誤,
   	使用已登出，請重新登入 - 已經呼叫登出API
       使用者不存在或使用者已登入 - 帳號找不到或使用者已經登入,
       餘額不足 - 提款金額高於餘額
   ]
   	403 -
   [
       無Token - header沒有JWT Token或是Token有誤
   ]	
   ```

## 測試資料

```
userName: Ryan
password: test
起始帳戶餘額: 0 元
```

需要增加側資可在

src\main\resources\data.sql增加

## Schema

目前僅設計一張表，沒有記錄提款取款的歷程

```sql
CREATE TABLE USER (
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  USER_NAME varchar(255) NOT NULL,
  PASSWORD varchar(255) NOT NULL,
  ACCOUNT DOUBLE NOT NULL DEFAULT 0,
  IS_LOGIN bit NOT NULL,
  CREATE_DATE date NOT NULL,
  UPDATE_DATE date,
  PRIMARY KEY (id)
);
```

