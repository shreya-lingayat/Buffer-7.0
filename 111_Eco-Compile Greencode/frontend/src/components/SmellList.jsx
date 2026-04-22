import React from 'react';
import { AlertTriangle, Info, Zap } from 'lucide-react';
import { motion } from 'framer-motion';

const SmellList = ({ smells, onSelect, selectedIndex }) => {
  return (
    <div className="space-y-4">
      <h4 className="text-slate-400 font-medium text-sm uppercase tracking-wider mb-4 flex items-center">
        <AlertTriangle className="w-4 h-4 mr-2 text-emerald-500" />
        Detected Inefficiencies
      </h4>
      {smells.map((smell, index) => (
        <motion.div
          key={index}
          whileHover={{ x: 5 }}
          onClick={() => onSelect(index)}
          className={`glass p-4 rounded-xl cursor-pointer transition-all border-l-4 ${
            selectedIndex === index ? 'border-emerald-500 bg-emerald-500/10' : 'border-transparent hover:bg-white/5'
          }`}
        >
          <div className="flex justify-between items-start">
            <div>
              <h5 className="font-bold text-emerald-100 flex items-center">
                {smell.smellType.replace('_', ' ')}
                <span className="ml-2 text-[10px] px-1.5 py-0.5 rounded bg-red-500/20 text-red-400 border border-red-500/20">
                  SEV {smell.severity}/10
                </span>
              </h5>
              <p className="text-xs text-slate-400 mt-1">Line {smell.lineNumber}</p>
            </div>
            <Zap className={`w-4 h-4 ${smell.severity > 7 ? 'text-red-400' : 'text-yellow-400'}`} />
          </div>
        </motion.div>
      ))}
    </div>
  );
};

export default SmellList;
