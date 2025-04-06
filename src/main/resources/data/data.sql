CREATE DATABASE spring_mini_project;

CREATE TABLE IF NOT EXISTS app_users (
   app_user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
   username VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL,
   level INT NOT NULL DEFAULT 0,
   xp INT NOT NULL DEFAULT 0,
   profile_image TEXT,
   is_verified BOOLEAN NOT NULL DEFAULT FALSE,
   created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS achievements (
                                            achievement_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                            title VARCHAR(255) NOT NULL,
                                            description VARCHAR(255),
                                            badge VARCHAR(255),
                                            xp_required INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS app_user_achievements (
                                                     app_user_achievement_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                     app_user_id uuid NOT NULL REFERENCES app_users ON DELETE CASCADE,
                                                     achievement_id uuid NOT NULL REFERENCES achievements ON DELETE CASCADE
);

CREATE TYPE frequency_type AS ENUM ('DAILY', 'WEEKLY', 'MONTHLY');

CREATE TABLE IF NOT EXISTS habits (
                                      habit_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                      title VARCHAR(255) NOT NULL,
                                      description VARCHAR(255),
                                      frequency frequency_type NOT NULL DEFAULT 'DAILY',
                                      is_active BOOLEAN NOT NULL DEFAULT TRUE,
                                      app_user_id uuid NOT NULL REFERENCES app_users ON DELETE CASCADE,
                                      created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TYPE habit_status AS ENUM ('COMPLETED', 'MISSED');

CREATE TABLE IF NOT EXISTS habit_logs (
                                          habit_log_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                          log_date DATE NOT NULL,
                                          status habit_status NOT NULL DEFAULT 'COMPLETED',
                                          xp_earned INT NOT NULL DEFAULT 0 ,
                                          habit_id uuid NOT NULL REFERENCES habits ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_verification (
                                                 verification_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                 app_user_id UUID NOT NULL REFERENCES app_users(app_user_id),
                                                 otp VARCHAR(255) NOT NULL,
                                                 expiration_time TIMESTAMP NOT NULL,
                                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                 CONSTRAINT fk_user FOREIGN KEY (app_user_id) REFERENCES app_users(app_user_id)
);