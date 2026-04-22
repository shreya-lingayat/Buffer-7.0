import React from 'react';
import { Lightbulb, Code, ArrowRight } from 'lucide-react';
import { motion, AnimatePresence } from 'framer-motion';

const RefactoringAdvice = ({ suggestion }) => {
  if (!suggestion) return (
    <div className="glass p-8 rounded-2xl flex flex-col items-center justify-center text-slate-500 italic h-full">
      <Lightbulb className="w-8 h-8 mb-4 opacity-20" />
      Select a smell to view optimization advice
    </div>
  );

  return (
    <AnimatePresence mode="wait">
      <motion.div 
        key={suggestion.description}
        initial={{ opacity: 0, x: 20 }}
        animate={{ opacity: 1, x: 0 }}
        exit={{ opacity: 0, x: -20 }}
        className="glass p-8 rounded-2xl h-full flex flex-col"
      >
        <div className="flex items-center mb-6">
          <div className="w-10 h-10 bg-emerald-500/20 rounded-lg flex items-center justify-center mr-4">
            <Lightbulb className="text-emerald-500 w-6 h-6" />
          </div>
          <div>
            <h4 className="text-xl font-bold text-emerald-500">Refactoring Advice</h4>
            <p className="text-xs text-slate-400 font-mono uppercase">Complexity: {suggestion.bigO}</p>
          </div>
        </div>

        <p className="text-slate-300 mb-8 leading-relaxed">
          {suggestion.description}
        </p>

        <div className="grid grid-cols-1 gap-6 flex-grow">
          <div className="space-y-2">
            <label className="text-[10px] font-bold text-red-400 uppercase tracking-widest flex items-center">
              <Code className="w-3 h-3 mr-1" /> Original Inefficient Code
            </label>
            <pre className="bg-red-500/5 p-4 rounded-xl border border-red-500/20 text-xs font-mono text-red-200 overflow-x-auto">
              {suggestion.codeSnippetBefore}
            </pre>
          </div>

          <div className="flex justify-center">
            <ArrowRight className="text-emerald-500/40" />
          </div>

          <div className="space-y-2">
            <label className="text-[10px] font-bold text-emerald-400 uppercase tracking-widest flex items-center">
              <Code className="w-3 h-3 mr-1" /> Green Optimized Pattern
            </label>
            <pre className="bg-emerald-500/5 p-4 rounded-xl border border-emerald-500/20 text-xs font-mono text-emerald-200 overflow-x-auto">
              {suggestion.codeSnippetAfter}
            </pre>
          </div>
        </div>
      </motion.div>
    </AnimatePresence>
  );
};

export default RefactoringAdvice;
