package com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.view;


public class UnitTrackingViewImpl{// extends Fragment implements UnitTrackingView, View.OnClickListener, SearchView.OnQueryTextListener {
/*
    private UnitTrackingPresenter presenter;
    private LinearLayout unitButton;
    private LinearLayout groupButton;
    private TextView unitTextButton;
    private TextView groupTextButton;
    private List<Unit> vehicles;
    private RecyclerView rvVehicles;
    private UnitTrackingAdapter adapterVehicles;
    private LinearLayoutManager layoutManagerVehicles;
    private List<Group> groups;
    private RecyclerView rvGroups;
    private GroupTrackingAdapter groupAdapter;
    private LinearLayoutManager layoutManagerGroups;
    private LinearLayout emptyGroupsImage;
    private ProgressBar progressBar;
    private CardView searchView;
    private androidx.appcompat.widget.SearchView searchViewTracking;
*/
/*
    public UnitTrackingViewImpl() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unit_tracking_view_impl, container, false);
        initUnitTrackingViewImpl(view);
        return view;
    }

    private void initUnitTrackingViewImpl(View view) {
        ImageView backButton = view.findViewById(R.id.toolbar_unit_tracking_img_back);
        ImageView toolbarImgSearch = view.findViewById(R.id.toolbar_unit_tracking_img_search);
        rvVehicles = view.findViewById(R.id.recycler_view_unit_tracking);
        rvGroups = view.findViewById(R.id.recycler_view_group_tracking);
        unitButton = view.findViewById(R.id.container_btn_unit_tracking_unit);
        groupButton = view.findViewById(R.id.container_btn_unit_tracking_groups);
        unitTextButton = view.findViewById(R.id.txt_unit_tracking_unit);
        groupTextButton = view.findViewById(R.id.txt_unit_tracking_group);
        emptyGroupsImage = view.findViewById(R.id.container_empty_groups_image);
        progressBar = view.findViewById(R.id.progress_bar_unit_tracking);
        searchView = view.findViewById(R.id.tracking_search_view_container);
        searchViewTracking = view.findViewById(R.id.search_view_tracking);


        unitButton.setOnClickListener(this);
        groupButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        toolbarImgSearch.setOnClickListener(this);
        searchViewTracking.setOnQueryTextListener(this);

        presenter = new UnitTrackingPresenterImpl();
        presenter.setView(this);
        presenter.getVehicles(getContext());
        presenter.getGroups(getContext());
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillVehiclesList(List<Unit> vehicles) {
        this.vehicles = vehicles;
        fillVehicles();
    }

    @Override
    public void fillGroupsList(List<Group> groups) {
        this.groups = groups;
        fillGroups();
    }

    @Override
    public void hideUnitRV() {
        rvVehicles.setVisibility(View.GONE);
    }

    @Override
    public void showUnitRV() {
        rvVehicles.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideGroupRV() {
        rvGroups.setVisibility(View.GONE);
    }

    @Override
    public void showGroupRV() {
        rvGroups.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyGroupsImage() {
        emptyGroupsImage.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyGroupsImage() {
        emptyGroupsImage.setVisibility(View.VISIBLE);
    }

    private void fillVehicles() {
        adapterVehicles = new UnitTrackingAdapter(vehicles, getContext());
        rvVehicles.setAdapter(adapterVehicles);
        layoutManagerVehicles = new LinearLayoutManager(getContext());
        rvVehicles.setLayoutManager(layoutManagerVehicles);
        //adapterVehiclesV2.setVehicleAndGroupSelectedListener(FragmentVehiclesV2.this);
    }

    private void fillGroups() {
        groupAdapter = new GroupTrackingAdapter(groups, getContext());
        rvGroups.setAdapter(groupAdapter);
        layoutManagerGroups = new LinearLayoutManager(getContext());
        rvGroups.setLayoutManager(layoutManagerGroups);
        // adapterGroupsV2.setGroupSelectedListener(FragmentVehiclesV2.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_unit_tracking_img_back:

                Toast.makeText(getContext(), "fdfdfdf", Toast.LENGTH_SHORT).show();

              /*  //Toast.makeText(getContext(), "fdfdfdf", Toast.LENGTH_SHORT).show();

             //   SharedPreferences pref;
             //   pref = PreferenceManager.getDefaultSharedPreferences(getContext());
              //  SharedPreferences.Editor editor = pref.edit();
               // editor.putBoolean("Refresh", true);
               // editor.apply();


                getActivity().onBackPressed();


               //  Bundle bndl = new Bundle();
              //  bndl.putString("nav", "TRACKING");
              //  Intent intent = new Intent(getContext(), MainMenuContainerActivity.class);
               // intent.putExtras(bndl);
               // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // startActivity(intent);

                // getActivity().onBackPressed();
                break;
            case R.id.toolbar_unit_tracking_img_search:
                searchView.setVisibility(View.VISIBLE);
                break;
            case R.id.container_btn_unit_tracking_unit:
                onClickUnitButton();
                break;
            case R.id.container_btn_unit_tracking_groups:
                onClickGroupButton();
                break;
        }
    }

    private List<Unit> getUnitList() {
        return UnitDB.getUnitList();
    }

    private void updateUnitDB(List<Unit> unitList) {
        for (Unit unit : unitList) {
            UnitDB.updateUnits(unit.getId(), unit.isVehicleStatus(), unit.getCveVehicle(), unit.getVehicleSwitch(), unit.getVehicleName(), unit.getVehicleImage(), unit.getSendTime(), unit.getDescBrand(), unit.getDescModel(), unit.getVehicleYear(), unit.getVehicleVin(), unit.getVehiclePlate(), unit.getGeoreference(), unit.getTimeTravel(), unit.getTimeElapsed(), unit.getLatitude(), unit.getLongitude(), unit.getMileage(), unit.getKmTravel(), unit.getCurrentSpeed(), unit.getMaxSpeed());
        }
    }

    private void onClickUnitButton() {
        presenter.getVehicles(getContext());

        unitButton.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
        groupButton.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        unitTextButton.setTextColor(getResources().getColor(R.color.colorWhite));
        groupTextButton.setTextColor(getResources().getColor(R.color.colorOrangeYellow));

        rvVehicles.setVisibility(View.VISIBLE);
        rvGroups.setVisibility(View.GONE);
        emptyGroupsImage.setVisibility(View.GONE);
    }

    private void onClickGroupButton() {
        presenter.getGroups(getContext());

        groupButton.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
        unitButton.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        groupTextButton.setTextColor(getResources().getColor(R.color.colorWhite));
        unitTextButton.setTextColor(getResources().getColor(R.color.colorOrangeYellow));

        rvGroups.setVisibility(View.VISIBLE);
        rvVehicles.setVisibility(View.GONE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Unit> filterVehicleList = filterVehicles(vehicles, newText);
        List<Group> filterGroupList = filterGroups(groups, newText);
        adapterVehicles.setFilter(filterVehicleList);
        groupAdapter.setFilter(filterGroupList);

        return true;
    }

    private List<Unit> filterVehicles(List<Unit> vehicles, String text) {
        List<Unit> filteredList = new ArrayList<>();
        text = text.toLowerCase();
        for (Unit vehicle : vehicles) {
            String vehicleName = vehicle.getVehicleName().toLowerCase();
            if (vehicleName.contains(text)) {
                filteredList.add(vehicle);
            }
        }
        return filteredList;
    }

    private List<Group> filterGroups(List<Group> groups, String text) {
        List<Group> filteredList = new ArrayList<>();
        text = text.toLowerCase();
        for (Group group : groups) {
            String groupName = group.getVehicle_group().toLowerCase();
            if (groupName.contains(text)) {
                filteredList.add(group);
            }
        }
        return filteredList;
    }

*/
}
