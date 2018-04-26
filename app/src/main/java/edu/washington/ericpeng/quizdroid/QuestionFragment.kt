package edu.washington.ericpeng.quizdroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [QuestionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionFragment : Fragment() {

    private lateinit var tv : TextView
    private lateinit var rg : RadioGroup
    private lateinit var btn : Button
    private var numCorrect : Int = 0
    private var num : Int = 0
    private var questions = ArrayList<Question>()

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            questions = arguments.getSerializable("questions") as ArrayList<Question>
            num = arguments.getInt("num")
            numCorrect = arguments.getInt("numCorrect")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v : View = inflater.inflate(R.layout.question_page, container, false)

        tv = v.findViewById(R.id.question)
        rg = v.findViewById(R.id.rg)
        btn = v.findViewById(R.id.submit)

        btn.isEnabled = false

        tv.text = questions[num].question

        for (item in questions[num].choices){
            val rb = RadioButton(v.context)
            rb.text = item
            rg.addView(rb)
        }

        var user = ""

        rg.setOnCheckedChangeListener { buttonView, isChecked ->

            val rb : RadioButton = v.findViewById(isChecked)

            if (rb.text.toString() == questions[num].answer)
                numCorrect += 1

            user = rb.text.toString()
            btn.isEnabled = true
        }

        btn.setOnClickListener({
            val fm = fragmentManager
            val ft = fm.beginTransaction()
            //ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left)
            ft.replace(R.id.container, AnswerFragment.newInstance(user, questions[num].answer,
                    numCorrect, questions, num))
            ft.commit()
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
         * @return A new instance of fragment QuestionFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(arg1 : ArrayList<Question>, num : Int, numCorrect : Int): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putSerializable("questions", arg1)
            args.putInt("num", num)
            args.putInt("numCorrect", numCorrect)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
