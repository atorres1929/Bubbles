package info.datahelix.bubbles;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnBubbleButtonInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BubbleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

//TODO Make it so that when you switch tabs, the title bar is reset to it's normal position

public class BubbleFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_BUBBLES_X = "bubbles_x";
    private static final String ARG_BUBBLES_Y = "bubbles_y";
    private static final String ARG_BUBBLES_R = "bubbles_r";
    private static final String ARG_BUBBLES_C = "bubbles_c";

    private int[] argBubblesX;
    private int[] argBubblesY;
    private int[] argBubblesR;
    private int[] argBubblesC;

    private Button bubbleButton;

    private RandomCirclesView randomCirclesView;

    private OnBubbleButtonInteractionListener mListener;

    public int bubblesToAdd;

    public BubbleFragment() {
        // Required empty public constructor
    }

    public void setBubbleButtonText(String text){
        bubbleButton.setText(text);
    }

    public static BubbleFragment newInstance(int[] bubblesX, int[] bubblesY, int[] bubblesR,int[] bubblesC) {
        BubbleFragment fragment = new BubbleFragment();
        Bundle args = new Bundle();
        args.putIntArray(ARG_BUBBLES_X, bubblesX);
        args.putIntArray(ARG_BUBBLES_Y, bubblesY);
        args.putIntArray(ARG_BUBBLES_R, bubblesR);
        args.putIntArray(ARG_BUBBLES_C, bubblesC);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            argBubblesX = getArguments().getIntArray(ARG_BUBBLES_X);
            argBubblesY = getArguments().getIntArray(ARG_BUBBLES_Y);
            argBubblesR = getArguments().getIntArray(ARG_BUBBLES_R);
            argBubblesC = getArguments().getIntArray(ARG_BUBBLES_C);
        }
        bubblesToAdd = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bubble, container, false);
        final ConstraintLayout bubbleLayout = (ConstraintLayout) view.findViewById(R.id.bubble_layout);
        bubbleButton = (Button) view.findViewById(R.id.bubble_button);
        bubbleButton.setOnClickListener(this);
        randomCirclesView = new RandomCirclesView(view.getContext());
        randomCirclesView.addCircles(argBubblesX, argBubblesY, argBubblesR, argBubblesC);

        /*
         * Needed to get the width and height of the screen.
         * The width and height are 0 before #onCreateView is finished
         */
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                randomCirclesView.post(new Runnable() {
                    @Override
                    public void run() {
                        randomCirclesView.setDimensions(bubbleLayout.getWidth(), bubbleLayout.getHeight());
                    }
                });
            }
        });
        bubbleLayout.addView(randomCirclesView);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == bubbleButton.getId()){
            randomCirclesView.addCircles(1);
            setBubbleButtonText("Bubbles! " + randomCirclesView.getNumCircles());
        }
    }

    public RandomCirclesView getRandomCirclesView(){return randomCirclesView;}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBubbleButtonInteractionListener) {
            mListener = (OnBubbleButtonInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBubbleButtonInteractionListener");
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
    public interface OnBubbleButtonInteractionListener {
        // TODO: Update argument type and name
        void onBubbleButtonInteraction();
    }

}
