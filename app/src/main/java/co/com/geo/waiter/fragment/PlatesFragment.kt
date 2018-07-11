package co.com.geo.waiter.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.geo.waiter.R
import co.com.geo.waiter.model.Plate


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_ORDER = "ARG_ORDER"


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlatesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PlatesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlatesFragment : Fragment() {

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param order Order if this fragment list represents an order.
         * @return A new instance of fragment PlatesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(order: TableOrder) =
                PlatesFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_ORDER, order)
                    }
                }
    }

    private var order: TableOrder? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            order = it.getSerializable(ARG_ORDER)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plates, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onPlateSelected(plate: Plate)
    }
}
