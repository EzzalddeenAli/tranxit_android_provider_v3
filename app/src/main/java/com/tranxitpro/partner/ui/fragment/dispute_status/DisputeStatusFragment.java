package com.tranxitpro.partner.ui.fragment.dispute_status;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tranxitpro.partner.R;
import com.tranxitpro.partner.base.BaseBottomSheetDialogFragment;
import com.tranxitpro.partner.data.network.model.Dispute;
import com.tranxitpro.partner.data.network.model.HistoryDetail;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisputeStatusFragment extends BaseBottomSheetDialogFragment implements DisputeStatusIView {

    @BindView(R.id.user_dispute)
    TextView userDispute;
    @BindView(R.id.admin_comment)
    TextView adminComment;
    @BindView(R.id.dispute_status)
    TextView disputeStatus;

    private static final String TRIP_KEY = "trip_data";
    private HistoryDetail datum;

    private DisputeStatusPresenter<DisputeStatusFragment> presenter = new DisputeStatusPresenter<>();

    public static DisputeStatusFragment newInstance(HistoryDetail datum) {
        Bundle args = new Bundle();
        DisputeStatusFragment fragment = new DisputeStatusFragment();
        args.putSerializable(TRIP_KEY, datum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_fragment_dispute_status;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        datum = (HistoryDetail) Objects.requireNonNull(bundle).getSerializable(TRIP_KEY);

        Dispute dispute = datum != null ? datum.getDispute() : null;
        if (dispute != null) {
            userDispute.setText(dispute.getDisputeName());
            adminComment.setText(dispute.getComments());
            disputeStatus.setText(dispute.getStatus().toUpperCase());
            if (dispute.getStatus().contains("open")) {
                disputeStatus.setTextColor(getResources().getColor(R.color.open_word));
                disputeStatus.setBackground(getResources().getDrawable(R.drawable.button_round_status_opened));
            } else {
                disputeStatus.setTextColor(getResources().getColor(R.color.close_word));
                disputeStatus.setBackground(getResources().getDrawable(R.drawable.button_round_status_closed));
            }
        }

    }
}
