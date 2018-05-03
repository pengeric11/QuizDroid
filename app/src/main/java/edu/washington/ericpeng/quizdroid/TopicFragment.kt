package edu.washington.ericpeng.quizdroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.util.Log
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

        val quizApp = QuizApp.create().getInstance()
        val topics : HashMap<String, Topic> = quizApp.topics
        val current : Topic = topics[topic] as Topic

        questions = current.questions

        tv_info.text = "Total Questions: " + questions.size.toString()
        tv.text = current.title
        tv_desc.text = current.longDescription


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
