package com.juanparedes.pruebameli

import com.juanparedes.pruebameli.data.remote.entity.mapToDomain
import com.juanparedes.pruebameli.data.remote.entity.mapToLocalEntity
import com.juanparedes.pruebameli.view.model.mapToPresentation
import org.junit.Assert.assertEquals
import org.junit.Test

class MappersTest {

    @Test
    fun `test ResultProduct_mapToPresentation`() {
        val expected = resultProduct.mapToPresentation()
        assertEquals(expected, presentationProduct)
    }

    @Test
    fun `test List_ResultProduct_mapToPresentation`() {
        val expectedList = listOf(resultProduct).mapToPresentation()
        val resulProductList = listOf(presentationProduct)
        assertEquals(expectedList, resulProductList)
    }

    @Test
    fun `test ResultProductApiDto_mapToDomain`() {
        val expected = response.mapToDomain()
        assertEquals(expected, resultProduct)
    }

    @Test
    fun `test List_ResultProductApiDto_mapToDomain`() {
        val expectedList = responseList.mapToDomain()
        val resulProductList = listOf(resultProduct)
        assertEquals(expectedList, resulProductList)
    }

    @Test
    fun `test ResultProductApiDto_mapToLocalEntity`() {
        val expected = response.mapToLocalEntity()
        assert(expected.price == productEntity.price)
        assert(expected.title == productEntity.title)
        assert(expected.thumbnail == productEntity.thumbnail)
        assert(expected.id == productEntity.id)
    }


}