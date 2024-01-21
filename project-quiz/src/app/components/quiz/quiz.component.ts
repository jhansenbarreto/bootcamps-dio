import { Component, OnInit } from '@angular/core';
import quiz_questions from '../../../assets/data/quiz_questions.json';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})

export class QuizComponent implements OnInit {

  title: string
  questions: any
  questionSelected: any
  answers: string[]
  answerSelected: string
  questionIndex: number
  maxQuestionIndex: number
  finished: boolean

  constructor() {
    this.title = ""
    this.questions = [];
    this.questionSelected = undefined
    this.answers = []
    this.answerSelected = ""
    this.questionIndex = 0
    this.maxQuestionIndex = 0
    this.finished = false
  }

  ngOnInit(): void {
    this.iniciar()
  }

  iniciar(): void {
    if (quiz_questions) {
      this.questionIndex = 0
      this.finished = false

      this.title = quiz_questions.title
      this.questions = quiz_questions.questions
      this.maxQuestionIndex = this.questions.length

      this.questionSelected = this.questions[this.questionIndex]
    }
  }

  buttonClicked(answer: string): void {
    this.answers.push(answer)
    this.nextStep()
  }

  nextStep(): void {
    this.questionIndex++
    if (this.maxQuestionIndex > this.questionIndex) {
      this.questionSelected = this.questions[this.questionIndex]
    } else {
      this.finished = true
      this.answerSelected = quiz_questions.results[this.checkResult() as keyof typeof quiz_questions.results]
    }
  }

  checkResult(): string {
    const result = this.answers.reduce((previous, current, i, array) => {
      if (array.filter(item => item === previous).length > array.filter(item => item === current).length) {
        return previous;
      } else {
        return current;
      }
    })
    return result
  }
}