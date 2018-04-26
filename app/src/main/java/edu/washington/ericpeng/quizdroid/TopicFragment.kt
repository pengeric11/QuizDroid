package edu.washington.ericpeng.quizdroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TopicFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TopicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopicFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private lateinit var topic: String
    private lateinit var tv_desc : TextView
    private lateinit var tv_info : TextView
    private lateinit var tv : TextView
    private var questions = ArrayList<Question>()

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            topic = arguments!!.getString("topic")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.topic_overview, container, false)

        tv = v.findViewById(R.id.tv_title)

        tv_desc = v.findViewById(R.id.tv_desc)
        tv_info = v.findViewById(R.id.tv_info)

        val btn : Button = v.findViewById(R.id.begin)

        tv.text = topic

        when(topic) {

            "Math" -> {
                tv_desc.text = "Lots of number, even more fun"
                questions.add(Question("In the complex number system, which of the following is equal to\n" +
                        "(14 − 2i)(7 + 12i)?", "74 + 154i", arrayListOf("74", "122", "74 + 154i", "122 + 154i")))
                questions.add(Question("Which of the following is a subset of  {b, c, d}",
                        "{ }", arrayListOf("{ }", "{a}", "{1 , 2 , 3}", "{a, b, c}")))
                questions.add(Question("The value of 5 in the number 357.21 is",
                        "5 tens", arrayListOf("5 tenths", "5 ones", "5 tens", "5 hundreds")))

                tv_info.text = "Total Questions: " + questions.size.toString()
            }

            "Physics" -> {
                tv_desc.text = "More than just gravity and Fig Newtons"

                questions.add(Question("Which of the following is a physical quantity that has a magnitude but no direction?"
                        , "Scalar", arrayListOf("Vector", "Frame of reference", "Resultant", "Scalar")))
                questions.add(Question("Multiplying or dividing vectors by scalars results in",
                        "Vectors", arrayListOf("Vectors if multiplied or scalars if divided",
                        "Scalars if multiplied scalars", "Scalars", "Vectors")))
                questions.add(Question("Which of the following is an example of a vector quantity?",
                        "Velocity", arrayListOf("Temperature", "Velocity", "Volume", "Mass")))
                questions.add(Question("For the winter, a duck flies 10.0 m/s due south against a " +
                        "gust of wind with a speed of 2.5 m/s. What is the resultant velocity of the duck?",
                        "7.5 m/s south", arrayListOf("-7.5 m/s south", "12.5 m/s south", "7.5 m/s south", "-12.5 m/s south")))

                tv_info.text = "Total Questions: " + questions.size.toString()
            }

            "Marvel Super Heroes" -> {
                tv_desc.text = "Probably the funnest quiz you'll ever take"

                questions.add(Question("In what place was Christmas once illegal?"
                        , "England", arrayListOf("Brazil", "Russia", "England", "France")))
                questions.add(Question("In California, it is illegal to eat oranges while doing what?",
                        "Bathing", arrayListOf("Gardening",
                        "Working on a computer", "Driving", "Bathing")))
                questions.add(Question("Coulrophobia means fear of what?",
                        "Clowns", arrayListOf("Clowns", " Old People", " cred Things\n", "Mass")))
                questions.add(Question("Which of the following is the longest running American animated TV show?",
                        "Simpsons", arrayListOf("Rugrats", " TV Funhouse", "Pokemon", "Simpsons")))
                questions.add(Question("What are the odds of being killed by space debris?",
                        "1 in 5 billion", arrayListOf("1 in 5 million", "1 in 5 billion", "1 in 10 billion", "1 in 1 trillion")))

                tv_info.text = "Total Questions: " + questions.size.toString()
            }
        }

        btn.setOnClickListener {
            val fm = fragmentManager
            val ft = fm.beginTransaction()
            //ft.setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left)
            ft.replace(R.id.container, QuestionFragment.newInstance(questions, 0, 0))
            ft.commit()
        }
        return v

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TopicFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String): TopicFragment {
            val fragment = TopicFragment()
            val args = Bundle()
            args.putString("topic", param1)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
