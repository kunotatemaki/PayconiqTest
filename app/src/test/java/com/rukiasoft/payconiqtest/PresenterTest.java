package com.rukiasoft.payconiqtest;

import com.rukiasoft.payconiqtest.model.ReposLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.model.StatusLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.model.UserLiveDataImplAndroid;
import com.rukiasoft.payconiqtest.network.NetworkManager;
import com.rukiasoft.payconiqtest.persistence.PersistenceManager;
import com.rukiasoft.payconiqtest.persistence.entities.Repo;
import com.rukiasoft.payconiqtest.persistence.entities.User;
import com.rukiasoft.payconiqtest.repolist.presenters.ReposPresenterImpl;
import com.rukiasoft.payconiqtest.repolist.ui.mainviews.ReposView;
import com.rukiasoft.payconiqtest.utils.PayconiqConstants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Roll on 14/8/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class PresenterTest {


    @Mock
    NetworkManager network;

    @Mock
    ReposLiveDataImplAndroid reposFull;

    @Mock
    UserLiveDataImplAndroid userFull;

    @Mock
    StatusLiveDataImplAndroid statusResponse;

    @Mock
    ReposLiveDataImplAndroid reposEmpty;

    @Mock
    UserLiveDataImplAndroid userEmpty;

    @Mock
    ReposView view;

    @Mock
    PersistenceManager persistenceManager;

    @Spy
    @InjectMocks
    ReposPresenterImpl presenter;


    @Before
    public void setUp(){
        configureLiveData();

    }

    @Test
    public void whenCacheHasDataLoadDataFromCache(){
        configureViewToReturnCachedData();
        presenter.loadRepos();
        verify(view, times(1)).setReposInView(reposFull.getLivedataValue());
        verify(presenter, times(0)).getNextBatchFromNetwork();
        verify(presenter, times(0)).loadDataFromLocalDatabse(PayconiqConstants.NICKNAME);
    }

    @Test
    public void whenCacheHasNoDataAndThereIsInternetConnectionLoadDataFromNetwork(){
        configureViewAndNetworkToReturnNetworkData();
        presenter.loadRepos();
        verify(presenter, times(1)).getNextBatchFromNetwork();
        verify(presenter, times(0)).loadDataFromLocalDatabse(PayconiqConstants.NICKNAME);
    }

    @Test
    public void whenCacheHasNoDataAndThereIsNoInternetConnectionLoadDataFromLocalDb(){
        configureViewAndNetworkToReturnDataFromDb();
        presenter.loadRepos();
        verify(presenter, times(0)).getNextBatchFromNetwork();
        verify(presenter, times(1)).loadDataFromLocalDatabse(PayconiqConstants.NICKNAME);
    }

    @Test
    public void showProgressBarWhenRequestingDataFromNetwork(){
        configureViewAndNetworkToReturnNetworkData();
        presenter.getNextBatchFromNetwork();
        verify(view, times(1)).showProgressBar();
    }

    @Test
    public void hideProgressBarWhenSettingDataInView(){
        presenter.handleChangesInObservedRepos(reposFull.getLivedataValue(), true);
        verify(view, times(1)).hideProgressBar();
    }

    @Test
    public void dataIsStorendInLocalDb(){
        presenter.handleChangesInObservedRepos(reposFull.getLivedataValue(), true);
        verify(persistenceManager, times(1)).insertListOfRepos(reposFull.getLivedataValue());
        presenter.handleChangesInObservedUser(userFull.getLivedataValue(), true);
        verify(persistenceManager, times(1)).insertUser(userFull.getLivedataValue());
    }

    private List<Repo> returnPopulatedListOfRepo(){
        List<Repo> repoList = new ArrayList<>();
        Repo repo1 = new Repo(1,1,"Repo1", "Description1");
        Repo repo2 = new Repo(2,1,"Repo2", "Description2");
        repoList.add(repo1);
        repoList.add(repo2);
        return repoList;
    }

    private User returnPopulatedUser(){
        return new User(1,"JakeWharton", "http://www.pathoftheimage.com");
    }

    private void configureLiveData(){
        //empty data
        when(reposEmpty.getLivedataValue()).thenReturn(null);
        when(userEmpty.getLivedataValue()).thenReturn(null);

        //full data
        List<Repo> repoList = returnPopulatedListOfRepo();
        when(reposFull.getLivedataValue()).thenReturn(repoList);
        User user = returnPopulatedUser();
        when(userFull.getLivedataValue()).thenReturn(user);
    }

    private void configureViewAndNetworkToReturnNetworkData(){
        when(view.getLiveRepos()).thenReturn(reposEmpty);
        when(view.getLiveUser()).thenReturn(userEmpty);
        when(network.isNetworkAvailable()).thenReturn(true);
    }

    private void configureViewAndNetworkToReturnDataFromDb(){
        when(view.getLiveRepos()).thenReturn(reposEmpty);
        when(view.getLiveUser()).thenReturn(userEmpty);
        when(network.isNetworkAvailable()).thenReturn(false);
    }

    private void configureViewToReturnCachedData(){
        when(view.getLiveRepos()).thenReturn(reposFull);
        when(view.getLiveUser()).thenReturn(userFull);
    }

    /*


    void handleChangesInObservedRepos(List<Repo> repos, boolean saveInLocalDatabase);

    void handleChangesInObservedUser(User user, boolean saveInLocalDatabase);

    void handleChangesInObservedStatus(PayconiqConstants.STATUS_RESPONSE status);
    */


}
