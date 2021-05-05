
package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Users;
import utils.DBUtil;

public class UsersValidator {
    public static List<String> validate(Users u, Boolean emailDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String email_error = validateEmail(u.getEmail(), emailDuplicateCheckFlag);
        if(!email_error.equals("")) {
            errors.add(email_error);
        }

        String username_error = validateUsername(u.getUsername());
        if(!username_error.equals("")) {
            errors.add(username_error);
        }

        String password_error = validatePassword(u.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    // Email
    private static String validateEmail(String email, Boolean emailDuplicateCheckFlag) {
        // 必須入力チェック
        if(email == null || email.equals("")) {
            return "メールアドレスを入力してください。";
        }

        // すでに登録されているメールアドレスとの重複チェック
        if(emailDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredEmail", Long.class).setParameter("email", email).getSingleResult();
            em.close();
            if(users_count > 0) {
                return "入力されたメールアドレスはすでに存在しています。";
            }
        }

        return "";
    }

    // ユーザー名の必須入力チェック
    private static String validateUsername(String username) {
        if(username == null || username.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

    // パスワードの必須入力チェック
    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        // パスワードを変更する場合のみ実行
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}