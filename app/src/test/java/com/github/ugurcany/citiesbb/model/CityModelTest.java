package com.github.ugurcany.citiesbb.model;

import android.content.Context;
import android.content.res.AssetManager;

import com.github.ugurcany.citiesbb.model.data.City;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CityModelTest {

    private final static String FILE_TEST_CITIES = "test_cities.json";

    @Mock
    Context mockContext;
    @Mock
    AssetManager mockAssetManager;

    private ICityModel cityModel;

    @Before
    public void setUp() throws Exception {
        Mockito.when(mockContext.getAssets())
                .thenReturn(mockAssetManager);
        Mockito.when(mockAssetManager.open(ArgumentMatchers.anyString()))
                .thenReturn(getClass().getClassLoader().getResourceAsStream(FILE_TEST_CITIES));

        cityModel = new CityModel(mockContext);
    }

    @Test
    public void testAllCitiesSize() {
        City[] allCities = cityModel.getAllCities();

        Assert.assertEquals(22, allCities.length);
    }

    @Test
    public void testAllCitiesOrder() {
        City[] allCities = cityModel.getAllCities();

        Assert.assertEquals("Alupka, UA",
                allCities[0].getDisplayName());
        Assert.assertEquals("Zavety Il’icha, RU",
                allCities[allCities.length - 1].getDisplayName());
    }

    @Test
    public void testSubCitiesSize_valid() {
        String prefix = "H";

        City[] someCities = cityModel.getCitiesStartingWith(prefix);
        Assert.assertEquals(2, someCities.length);

        Assert.assertEquals("Holubynka, UA",
                someCities[0].getDisplayName());
        Assert.assertEquals("Hurzuf, UA",
                someCities[1].getDisplayName());
    }

    @Test
    public void testSubCitiesSize_invalid() {
        String prefix = "Mea";

        City[] someCities = cityModel.getCitiesStartingWith(prefix);
        Assert.assertEquals(0, someCities.length);
    }

}