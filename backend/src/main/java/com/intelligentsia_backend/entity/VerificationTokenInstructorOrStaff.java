package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
@Data
@NoArgsConstructor
@Entity
public class VerificationTokenInstructorOrStaff {

        //Expiration time 10 miutes
        private static  final int EXPIRATION_TIME = 4320;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String token;

        private Date expirationTime;

       private String mail;

        public VerificationTokenInstructorOrStaff(String token,String email) {
            super();
            this.token = token;
            this.mail=email;
            this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
        }

        private Date calculateExpirationDate(int expirationTime) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(new Date().getTime());
            calendar.add(Calendar.MINUTE, expirationTime);
            return new Date(calendar.getTime().getTime());
        }

}

