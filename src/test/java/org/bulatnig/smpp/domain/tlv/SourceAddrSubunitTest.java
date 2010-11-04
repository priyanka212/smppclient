package org.bulatnig.smpp.domain.tlv;

import junit.framework.JUnit4TestAdapter;
import static org.junit.Assert.assertEquals;

import org.bulatnig.smpp.util.SmppByteBuffer;
import org.junit.Test;
import org.bulatnig.smpp.pdu.tlv.*;
import org.bulatnig.smpp.util.WrongParameterException;

public class SourceAddrSubunitTest {

	// Used for backward compatibility (IDEs, Ant and JUnit 3 text runner)
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SourceAddrSubunitTest.class);
	}
	
	@Test
	public void testSASConstructor1() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x000D);
		bb.appendShort(0x0001);
		bb.appendByte((byte)0x04);
		SourceAddrSubunit sas = new SourceAddrSubunit(bb.array());
		assertEquals(ParameterTag.SOURCE_ADDR_SUBUNIT, sas.getTag());
		assertEquals(5, sas.getBytes().length);
		assertEquals(AddrSubunit.EXTERNAL_UNIT_1, sas.getValue());
		assertEquals("000d000104", new SmppByteBuffer(sas.getBytes()).getHexDump());
	}

    @Test(expected = TLVNotFoundException.class)
	public void testSASConstructor2() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0000);
		bb.appendShort(0x0002);
		bb.appendByte((byte)0x55);
		new SourceAddrSubunit(bb.array());
	}
	
	@Test(expected= TLVException.class)
	public void testSASConstructor3() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x000D);
		bb.appendShort(0x0001);
		bb.appendShort(0x0003);
		new SourceAddrSubunit(bb.array());
	}
	
	@Test
	public void testSASConstructor4() throws TLVException, WrongParameterException {
		SourceAddrSubunit sas = new SourceAddrSubunit(AddrSubunit.MOBILE_EQUIPMENT);
		assertEquals(ParameterTag.SOURCE_ADDR_SUBUNIT, sas.getTag());
		assertEquals(5, sas.getBytes().length);
		assertEquals(AddrSubunit.MOBILE_EQUIPMENT, sas.getValue());
		assertEquals("000d000102", new SmppByteBuffer(sas.getBytes()).getHexDump());
	}
	
	@Test(expected= TLVException.class)
	public void testSASConstructor5() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x000D);
		bb.appendShort(0x0002);
		bb.appendShort(0x0012);
		new SourceAddrSubunit(bb.array());
	}
	
	@Test(expected=ClassCastException.class)
	public void testSASConstructor6() throws WrongParameterException, TLVException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0006);
		bb.appendShort(0x0001);
		bb.appendByte((byte)0x00);
		new SourceAddrSubunit(bb.array());
	}
	
	@Test
	public void testSASConstructor7() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x000D);
		bb.appendShort(0x0001);
		bb.appendByte((byte)0x12);
		SourceAddrSubunit sas = new SourceAddrSubunit(bb.array());
		assertEquals(ParameterTag.SOURCE_ADDR_SUBUNIT, sas.getTag());
		assertEquals(5, sas.getBytes().length);
		assertEquals(AddrSubunit.RESERVED, sas.getValue());
		assertEquals((short) 18, sas.getIntValue());
		assertEquals("000d000112", new SmppByteBuffer(sas.getBytes()).getHexDump());
	}
	
}