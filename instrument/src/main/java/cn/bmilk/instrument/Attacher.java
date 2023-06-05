package cn.bmilk.instrument;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class Attacher {
    public static void main(String[] args) throws AgentLoadException, IOException, AgentInitializationException, AttachNotSupportedException {
        VirtualMachine vm = VirtualMachine.attach("1608");
        vm.loadAgent("D:\\git\\demo\\instrument\\target\\instrument-1.0-SNAPSHOT.jar");
    }
}
