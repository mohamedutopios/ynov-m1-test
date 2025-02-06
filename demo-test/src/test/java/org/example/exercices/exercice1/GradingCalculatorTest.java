package org.example.exercices.exercice1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GradingCalculatorTest {

    private GradingCalculator gradingCalculator;


    private void initGradingCalculator(int score, int attendance) {
        gradingCalculator = new GradingCalculator();
        gradingCalculator.setScore(score);
        gradingCalculator.setAttendancePercentage(attendance);
    }

    @Test
    @DisplayName("Test Attendance 90 - Score 95 - Garde A")
    void givenAttendance90AndScore95WhenGetGradeThenGradeIsA() {

        //Arrange
        initGradingCalculator(95,90);

        // Act
        char grade = gradingCalculator.getGrade();

        // Assert
        Assertions.assertEquals('A', grade);
    }

    @Test
    @DisplayName("Test Attendance 90 - Score 85 - Garde B")
    void givenAttendance90AndScore85WhenGetGradeThenGradeIsB() {

        // Arrange
        initGradingCalculator(85,90);

        // Act
        char grade = gradingCalculator.getGrade();

        // Assert
        Assertions.assertEquals('B', grade);
    }

    @Test
    @DisplayName("Test Attendance 90 - Score 65 - Garde C")
    void givenAttendance90AndScore65WhenGetGradeThenGradeIsC() {

        // Arrange
        initGradingCalculator(65,90);

        // Act
        char grade = gradingCalculator.getGrade();

        // Assert
        Assertions.assertEquals('C', grade);
    }

    @Test
    @DisplayName("Test Attendance 95 - Score 65 - Garde B")
    void givenAttendance65AndScore95WhenGetGradeThenGradeIsB() {


        initGradingCalculator(95,65);
        char grade = gradingCalculator.getGrade();

        Assertions.assertEquals('B', grade);
    }

    @Test
    @DisplayName("Test Attendance 55 - Score 95 - Garde F")
    void givenAttendance55AndScore95WhenGetGradeThenGradeIsF() {


        initGradingCalculator(95,55);

        char grade = gradingCalculator.getGrade();

        Assertions.assertEquals('F', grade);
    }

    @Test
    @DisplayName("Test Attendance 55 - Score 65 - Garde F")
    void givenAttendance55AndScore65WhenGetGradeThenGradeIsF() {


        initGradingCalculator(65,55);

        char grade = gradingCalculator.getGrade();

        Assertions.assertEquals('F', grade);
    }

    @Test
    @DisplayName("Test Attendance 90 - Score 50 - Garde F")
    void givenAttendance90AndScore50WhenGetGradeThenGradeIsA() {


        initGradingCalculator(50,90);

        char grade = gradingCalculator.getGrade();

        Assertions.assertEquals('F', grade);
    }
}


