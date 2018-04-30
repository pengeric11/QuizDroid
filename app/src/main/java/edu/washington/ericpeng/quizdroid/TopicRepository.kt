package edu.washington.ericpeng.quizdroid

class TopicRepository {

    val topics = HashMap<String, Topic>()
    //private val instance : TopicRepository = TopicRepository()
    companion object {
        fun create(): TopicRepository = TopicRepository().getInstance()
    }

    fun getInstance() : TopicRepository {

        val mQuestions : ArrayList<Question> = ArrayList()

        mQuestions.add(Question("In the complex number system, which of the following is equal to\n" +
                "(14 − 2i)(7 + 12i)?","74", "122", "74 + 154i", "122 + 154i", 3))
        mQuestions.add(Question("Which of the following is a subset of  {b, c, d}", "{ }",
                "{a}", "{1 , 2 , 3}", "{a, b, c}", 1))
        mQuestions.add(Question("The value of 5 in the number 357.21 is",
                "5 tenths", "5 ones", "5 tens", "5 hundreds", 3))

        val math = Topic(mQuestions, "Math", "Numbers", "Lots of number, even more fun")

        val pQuestions : ArrayList<Question> = ArrayList()

        pQuestions.add(Question("Which of the following is a physical quantity that has a magnitude but no direction?",
                "Vector", "Frame of reference", "Resultant", "Scalar", 4))
        pQuestions.add(Question("Multiplying or dividing vectors by scalars results in",
                "Vectors if multiplied or scalars if divided",
                "Scalars if multiplied scalars", "Scalars", "Vectors", 4))
        pQuestions.add(Question("Which of the following is an example of a vector quantity?",
                "Temperature", "Velocity", "Volume", "Mass", 2))
        pQuestions.add(Question("For the winter, a duck flies 10.0 m/s due south against a " +
                "gust of wind with a speed of 2.5 m/s. What is the resultant velocity of the duck?",
                "-7.5 m/s south", "12.5 m/s south", "7.5 m/s south", "-12.5 m/s south", 3))

        val physics = Topic(pQuestions, "Physics", "Gravity", "More than just gravity and Fig Newtons")

        val sQuestions : ArrayList<Question> = ArrayList()

        sQuestions.add(Question("In what place was Christmas once illegal?",
                "Brazil", "Russia", "England", "France", 3))
        sQuestions.add(Question("In California, it is illegal to eat oranges while doing what?",
                "Gardening", "Working on a computer", "Driving", "Bathing", 4))
        sQuestions.add(Question("Coulrophobia means fear of what?",
                "Clowns", " Old People", " Random Things\n", "Mass", 1))
        sQuestions.add(Question("Which of the following is the longest running American animated TV show?",
                "Rugrats", " TV Funhouse", "Pokemon", "Simpsons", 4))
        sQuestions.add(Question("What are the odds of being killed by space debris?",
                "1 in 5 million", "1 in 5 billion", "1 in 10 billion", "1 in 1 trillion", 2))

        val marvel = Topic(sQuestions, "Marvel Super Heroes", "Marvel", "Probably the funnest quiz you'll ever take")

        topics.put("Math", math)
        topics.put("Physics", physics)
        topics.put("Marvel Super Heroes", marvel)

        return this
    }
}
