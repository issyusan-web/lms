
-- ① ユーザー
CREATE TABLE users (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role INTEGER NOT NULL CHECK (role IN (1, 2, 3)),
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

-- ② 研修
CREATE TABLE trainings (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    is_finalized BOOLEAN NOT NULL DEFAULT FALSE,
    CHECK (end_date >= start_date)
);

-- ③ 研修と講師の紐付け
CREATE TABLE training_instructors (
    training_id INTEGER,
    instructor_id INTEGER,
    PRIMARY KEY (training_id, instructor_id),
    FOREIGN KEY (training_id) REFERENCES trainings(id),
    FOREIGN KEY (instructor_id) REFERENCES users(id)
);

-- ④ 研修と受講者の紐付け
CREATE TABLE training_students (
    training_id INTEGER,
    student_id INTEGER,
    PRIMARY KEY (training_id, student_id),
    FOREIGN KEY (training_id) REFERENCES trainings(id),
    FOREIGN KEY (student_id) REFERENCES users(id)
);

-- ⑤ 課題
CREATE TABLE assignments (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description TEXT,
    due_date TIMESTAMP NOT NULL,
    training_id INTEGER NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    allow_late_submission BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (training_id) REFERENCES trainings(id)
);

-- ⑥ 課題提出・評価
CREATE TABLE submissions (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id INTEGER NOT NULL,
    assignment_id INTEGER NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    submitted_at TIMESTAMP NOT NULL,
    grade VARCHAR(1) CHECK (grade IN ('A', 'B', 'C')),
    feedback TEXT,
    FOREIGN KEY (student_id) REFERENCES users(id),
    FOREIGN KEY (assignment_id) REFERENCES assignments(id),
    UNIQUE (student_id, assignment_id)
);

-- ⑦ 出欠
CREATE TABLE attendance (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id INTEGER NOT NULL,
    training_id INTEGER NOT NULL,
    attendance_date DATE NOT NULL,
    status INTEGER NOT NULL CHECK (status IN (1, 2, 3, 4)),
    FOREIGN KEY (training_id, student_id)
        REFERENCES training_students(training_id, student_id),
    UNIQUE (student_id, training_id, attendance_date)
);
