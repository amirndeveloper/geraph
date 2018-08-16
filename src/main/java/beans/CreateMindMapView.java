package beans;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class CreateMindMapView implements Serializable {

    private MindmapNode root;

    private MindmapNode selectedNode;

    private NodeData nodeData;

    private MindmapNode selectedMindMap;

    private String selectedMode = CreatGraphMode.VIEW;

    public CreateMindMapView() {

        root = new DefaultMindmapNode("root", "root", "FFCC00", true);

        nodeData = new NodeData();
//
//        MindmapNode ips = new DefaultMindmapNode("IPs", "IP Numbers", "6e9ebf", true);
//        MindmapNode ns = new DefaultMindmapNode("NS(s)", "Namespaces", "6e9ebf", true);
//        MindmapNode malware = new DefaultMindmapNode("Malware", "Malicious Software", "6e9ebf", true);
//
//        root.addNode(ips);
//        root.addNode(ns);
//        root.addNode(malware);
    }

    public void createNode()
    {

            MindmapNode newNode = new DefaultMindmapNode(nodeData.getName(),nodeData.getDescription(),nodeData.getColor(),true);
            selectedNode.addNode(newNode);

            nodeData = new NodeData();



    }

    public void removeNode()

    {
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);
    }

    public MindmapNode getRoot() {
        return root;
    }

    public MindmapNode getSelectedNode() {
        return selectedNode;
    }
    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();


    }

    public void setMode(String mode)
    {
        this.selectedMode = mode;
    }

    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();
        RequestContext context = RequestContext.getCurrentInstance();
        if (selectedMode.equals(CreatGraphMode.ADD))
        {
            //addNodeDialog
            context.execute("PF('addNodeDialog').show();");
        }
        else {

            if (selectedMode.equals(CreatGraphMode.remove))
            {
                //removeNodeDialog
                context.execute("PF('removeNodeDialog').show();");
            }

            else {
                context.execute("PF('details').show();");
            }


        }

    }

    public NodeData getNodeData() {
        return nodeData;
    }

    public void setNodeData(NodeData nodeData) {
        this.nodeData = nodeData;
    }

    public MindmapNode getSelectedMindMap() {
        return selectedMindMap;
    }

    public void setSelectedMindMap(MindmapNode selectedMindMap) {
        this.selectedMindMap = selectedMindMap;
    }

    public String getSelectedMode() {
        return selectedMode;
    }

    public void setSelectedMode(String selectedMode) {
        this.selectedMode = selectedMode;
    }
}