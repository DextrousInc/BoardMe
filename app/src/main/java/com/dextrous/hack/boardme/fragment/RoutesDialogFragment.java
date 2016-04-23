package com.dextrous.hack.boardme.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.Route;
import com.dextrous.hack.boardme.response.GenericListResponse;

import java.text.MessageFormat;
import java.util.List;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.INTENT_PARAM_ROUTES_RESPONSE_KEY;

/**
 * A simple {@link DialogFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RouteSelectionFragmentListener} interface
 * to handle interaction events.
 * Use the {@link RoutesDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoutesDialogFragment extends DialogFragment {
    private List<Route> allRoutes;

    private RouteSelectionFragmentListener mListener;

    public RoutesDialogFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RoutesDialogFragment newInstance(GenericListResponse<Route> routeResponse) {
        RoutesDialogFragment fragment = new RoutesDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(INTENT_PARAM_ROUTES_RESPONSE_KEY, routeResponse);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final RouteSelectionFragmentListener routeSelectionFragmentListener= (RouteSelectionFragmentListener)getActivity();
        GenericListResponse<Route> routesResponse = (GenericListResponse<Route>) getArguments().getSerializable(INTENT_PARAM_ROUTES_RESPONSE_KEY);
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_routes_dialog, null);
        // populate the route options
        final RadioGroup routesRadioGroup = (RadioGroup) view.findViewById(R.id.routesRadioGroup);
        allRoutes = routesResponse.getItems();

        RadioGroup.LayoutParams radioLayoutParams;
        for (Route route: allRoutes) {
            radioLayoutParams= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            RadioButton routeRadioBtn = new RadioButton(getActivity());
            routeRadioBtn.setId(route.getId());
            routeRadioBtn.setText(MessageFormat.format(BoardMeConstants.MSG_ROUTE_INFO_WITH_START_END,
                    new Object[]{
                            route.getRouteName() ,
                            route.getRouteStart() ,
                            route.getRouteEnd()
                    }));
            routesRadioGroup.addView(routeRadioBtn, radioLayoutParams);
        }
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.title_fragment_routes)
                .setNegativeButton(
                        android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .setPositiveButton(
                        android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do send selected response to the parent activity
                                int selectedRouetId = routesRadioGroup.getCheckedRadioButtonId();
                                if (selectedRouetId != -1) {
                                    Route selectedRoute = allRoutes.get(0);
                                    for (Route route :
                                            allRoutes) {
                                        if (route.getId() == selectedRouetId) {
                                            selectedRoute = route;
                                            break;
                                        }
                                    }
                                    routeSelectionFragmentListener.onRouteSelectConfirmation(selectedRoute);
                                } else {
                                    Toast.makeText(getContext(), BoardMeConstants.MSG_WARN_ROUTE_NOT_SELECTED, Toast.LENGTH_LONG ).show();
                                }
                                dialog.dismiss();
                            }
                        })
                .create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RouteSelectionFragmentListener) {
            mListener = (RouteSelectionFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement RouteSelectionFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface RouteSelectionFragmentListener {
        void onRouteSelectConfirmation(Route selectedRoute);
    }
}
