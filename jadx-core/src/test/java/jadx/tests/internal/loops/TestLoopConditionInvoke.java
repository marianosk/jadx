package jadx.tests.internal.loops;

import jadx.api.InternalJadxTest;
import jadx.core.dex.nodes.ClassNode;

import org.junit.Test;

import static jadx.tests.utils.JadxMatchers.containsOne;
import static org.junit.Assert.assertThat;

public class TestLoopConditionInvoke extends InternalJadxTest {

	public static class TestCls {
		private static final char STOP_CHAR = 0;
		private int pos;

		private boolean test(char lastChar) {
			int startPos = pos;
			char ch;
			while ((ch = next()) != STOP_CHAR) {
				if (ch == lastChar) {
					return true;
				}
			}
			pos = startPos;
			return false;
		}

		private char next() {
			return 0;
		}
	}

	@Test
	public void test() {
		ClassNode cls = getClassNode(TestCls.class);
		String code = cls.getCode().toString();
		System.out.println(code);

		assertThat(code, containsOne("do {"));
		assertThat(code, containsOne("if (ch == '\\u0000') {"));
		assertThat(code, containsOne("this.pos = startPos;"));
		assertThat(code, containsOne("return false;"));
		assertThat(code, containsOne("} while (ch != lastChar);"));
		assertThat(code, containsOne("return true;"));
	}
}