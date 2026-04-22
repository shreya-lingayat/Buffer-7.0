import React, { useMemo } from 'react';
import ReactFlow, { Background, Controls } from 'reactflow';
import 'reactflow/dist/style.css';

const StructuralGraph = ({ smells }) => {
  const { nodes, edges } = useMemo(() => {
    const initialNodes = [
      {
        id: 'start',
        data: { label: 'Source File' },
        position: { x: 250, y: 0 },
        style: { background: '#10b981', color: '#fff', borderRadius: '8px' },
      },
    ];

    const initialEdges = [];

    smells.forEach((smell, index) => {
      const id = `smell-${index}`;
      initialNodes.push({
        id: id,
        data: { label: `${smell.smellType}\nLine ${smell.lineNumber}` },
        position: { x: 100 + index * 300, y: 150 },
        style: { 
          background: smell.severity > 7 ? '#ef4444' : '#f59e0b', 
          color: '#fff', 
          borderRadius: '8px',
          fontSize: '10px',
          padding: '10px'
        },
      });

      initialEdges.push({
        id: `e-start-${id}`,
        source: 'start',
        target: id,
        animated: true,
        style: { stroke: '#10b981' },
      });
    });

    return { nodes: initialNodes, edges: initialEdges };
  }, [smells]);

  return (
    <div className="glass rounded-2xl h-[400px] w-full overflow-hidden">
      <div className="p-4 border-b border-white/10 bg-white/5 flex justify-between items-center">
        <h4 className="text-xs font-bold text-slate-400 uppercase tracking-widest">Structural Carbon Hotspots</h4>
        <span className="text-[10px] text-emerald-500 font-mono">LIVE GRAPH VIEW</span>
      </div>
      <ReactFlow
        nodes={nodes}
        edges={edges}
        fitView
        nodesDraggable={false}
        zoomOnScroll={false}
      >
        <Background color="#1e293b" gap={20} />
      </ReactFlow>
    </div>
  );
};

export default StructuralGraph;
