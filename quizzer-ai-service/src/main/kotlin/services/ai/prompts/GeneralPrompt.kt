package com.example.services.ai.prompts

object GeneralPrompt {
    fun getPrompt(content: String): String {
        val generalPromptText =
            "Сгенерируй тест в виде JSON-структуры, соответствующей следующим Kotlin data class:\n" +
                    "\n" +
                    "data class Test(\n" +
                    "    val name: String,\n" +
                    "    val questions: List<IQuestion>,\n" +
                    "    val tags: List<String> = emptyList(),\n" +
                    ")\n" +
                    "\n" +
                    "sealed interface IQuestion {\n" +
                    "    val questionId: String\n" +
                    "    val type: QuestionType\n" +
                    "    val text: String\n" +
                    "    val points: Int\n" +
                    "}\n" +
                    "\n" +
                    "data class MultipleChoiceQuestion(\n" +
                    "    override val questionId: String = ObjectId().toString(),\n" +
                    "    override val type: QuestionType = QuestionType.MULTIPLE_CHOICE,\n" +
                    "    override val text: String,\n" +
                    "    override val points: Int,\n" +
                    "    val options: List<String>,\n" +
                    "    val correctAnswerIndices: List<Int>\n" +
                    ") : IQuestion\n" +
                    "\n" +
                    "data class SingleWordAnswerQuestion(\n" +
                    "    override val questionId: String = ObjectId().toString(),\n" +
                    "    override val type: QuestionType = QuestionType.SINGLE_WORD,\n" +
                    "    override val text: String,\n" +
                    "    override val points: Int,\n" +
                    "    val correctAnswer: String,\n" +
                    ") : IQuestion\n" +
                    "\n" +
                    "enum class QuestionType {\n" +
                    "    MULTIPLE_CHOICE,\n" +
                    "    SINGLE_WORD\n" +
                    "}\n" +
                    "\n" +
                    "Требования:\n" +
                    "1. Используй следующий отрывок текста для формулировки вопросов: $content\n" +
                    "2. Создай 10 вопросов, из них:\n" +
                    "   - 6 вопросов типа MULTIPLE_CHOICE (с 4 вариантами ответа, где 1-2 правильных)\n" +
                    "   - 4 вопроса типа SINGLE_WORD (короткий ответ одним словом/фразой)\n" +
                    "3. Вопросы должны проверять понимание текста, важных деталей и терминов\n" +
                    "4. Для MULTIPLE_CHOICE варианты ответов должны быть правдоподобными, но только 1-2 верными\n" +
                    "5. Для SINGLE_WORD ответы должны быть краткими и однозначными\n" +
                    "6. points для вопросов установи от 1 до 3 в зависимости от сложности\n" +
                    "7. name теста должен отражать тему текста\n" +
                    "8. Добавь соответствующие теги (tags) по теме\n" +
                    "9. authorId и created сгенерируй случайными (authorId - ObjectId, created - текущая дата)\n" +
                    "10. Предоставь результат в виде валидного JSON, готового к парсингу в Kotlin-объект\n"
        return generalPromptText
    }

}