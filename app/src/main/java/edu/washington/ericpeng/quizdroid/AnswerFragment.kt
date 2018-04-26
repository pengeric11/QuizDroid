package edu.washington.ericpeng.quizdroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AnswerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AnswerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnswerFragment : Fragment() {

    private lateinit var  answer : String
    private var numCorrect : Int = 0
    private var num : Int = 0
    private lateinit var user : String
    private var questions = ArrayList<Question>()

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            user = arguments.getString("user")
            answer = arguments.getString("answer")
            numCorrect = arguments.getInt("numCorrect")
            num = arguments.getInt("num")
            questions = arguments.getSerializable("questions") as ArrayList<Question>
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.answer_page, container, false)

        val btn : Button = v.findViewById(R.id.btn)
        val tv1 : TextView = v.findViewById(R.id.answer)
        val tv2 : TextView = v.findViewById(R.id.total)
        val tv3 : TextView = v.findViewById(R.id.correct)

        tv1.text = "Your answer was " + user
        tv3.text = "The correct answer is " + answer
        tv2.text = "You've gotten " + numCorrect + " correct out of " + questions.size  + " questions"


        if (num < questions.size - 1)
            btn.text = "Next"
        else btn.text = "Finish"

        btn.setOnClickListener({
            if (num == questions.size - 1){
                activity.finish()
            }

            else {
                val fm = fragmentManager
                val ft = fm.beginTransaction()
                //ft.setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left)
                ft.replace(R.id.container, QuestionFragment.newInstance(questions, num + 1, numCorrect))
                ft.commit()
            }
        })

        return v
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
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
         * @return A new instance of fragment AnswerFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(user : String, answer : String, numCorrect: Int, questions : ArrayList<Question>, num : Int): AnswerFragment {
            val fragment = AnswerFragment()
            val args = Bundle()
            args.putString("user", user)
            args.putString("answer", answer)
            args.putInt("numCorrect", numCorrect)
            args.putSerializable("questions", questions)
            args.putInt("num", num)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
